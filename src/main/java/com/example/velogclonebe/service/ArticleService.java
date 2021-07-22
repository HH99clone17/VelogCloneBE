package com.example.velogclonebe.service;


import com.example.velogclonebe.domain.dto.request.ArticleRequestDto;
import com.example.velogclonebe.domain.dto.request.ArticleUpdateRequestDto;
import com.example.velogclonebe.domain.dto.response.ArticleListResponseDto;
import com.example.velogclonebe.domain.dto.response.ArticleResponseDto;
import com.example.velogclonebe.domain.dto.response.CommentGetResponseDto;
import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.domain.entity.Comment;
import com.example.velogclonebe.domain.repository.ArticleRepository;
import com.example.velogclonebe.domain.repository.CommentRepository;
import com.example.velogclonebe.domain.repository.UserRepository;
import com.example.velogclonebe.exception.ApiRequestException;
import com.example.velogclonebe.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;


    // 게시글 리스트 조회
    @Transactional
    public List<ArticleListResponseDto> getArticles() {

        List<Article> articleList = articleRepository.findAllByOrderByCreatedAtDesc();
        // List<Article> articleList = articleRepository.findAllAndCountComment();

        List<ArticleListResponseDto> responseDto = articleList.stream()
                .map(article -> new ArticleListResponseDto(article, commentRepository.findAllByArticleOrderByCreatedAtDesc(article)))
                .collect(Collectors.toList());

        return responseDto;

    }

    // 게시글 작성
    @Transactional
    public void setArticle(String title, String text, String textHtml, String textMarkdown, MultipartFile file, String username) throws IOException {

        String imageUrl = s3Uploader.upload(file, "static");
        String profileUrl = userRepository.findByUsername(username).getProfileUrl();

        // System.out.println("title ::::::::::::::::: " + title);
        // System.out.println("text ::::::::::::::::: " + text);
        // System.out.println("textHtml ::::::::::::::::: " + textHtml);
        // System.out.println("textMarkdown ::::::::::::::::: " + textMarkdown);
        // System.out.println("imageUrl ::::::::::::::::: " + imageUrl);
        // System.out.println("username ::::::::::::::::: " + username);
        // System.out.println("profileUrl ::::::::::::::::: " + profileUrl);


        Article article = new Article(title, text, textHtml, textMarkdown, imageUrl, username, profileUrl);
        articleRepository.save(article);
    }

    // 게시글 수정
    @Transactional
    public void updateArticle(Long articleId, ArticleUpdateRequestDto articleUpdateRequestDto, String username) {

        Article article = articleRepository.findById(articleId).orElseThrow(
                ()  -> new ApiRequestException("해당 글이 존재하지 않습니다."));

        if (!article.getUsername().equals(username)) {
            throw new ApiRequestException("수정 권한이 없습니다.");
        }

        article.update(articleUpdateRequestDto);
    }

    // 게시글 삭제
    @Transactional
    public void deleteArticle(Long articleId, String username) {

        Article article = articleRepository.findById(articleId).orElseThrow(
                ()->new ApiRequestException("해당 글이 존재하지 않습니다."));

        if (!article.getUsername().equals(username)) {
            throw new ApiRequestException("삭제 권한이 없습니다.");
        }


        // 글 삭제시 해당 글에 연관된 댓글들 삭제 (commentRepository 미반영)
        commentRepository.deleteAllByArticle(article);
        articleRepository.deleteById(articleId);
        s3Uploader.deletes3(article.getImageUrl());
    }

    // 게시글 상세페이지 요청 처리
    @Transactional
    public ArticleResponseDto getArticleDetail(Long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(
                ()->new ApiRequestException("해당 글이 존재하지 않습니다."));

        // 게시글에 해당하는 댓글 리스트로 받아옴
        List<Comment> commentList = commentRepository.findAllByArticleOrderByCreatedAtDesc(article);

        // Dto로 넘겨주기 위한 작업
        List<CommentGetResponseDto> commentDtoList = commentList.stream()
                .map(CommentGetResponseDto::new)
                .collect(Collectors.toList());

        // 게시글 응답을 위해 articleresponsedto를 생성해서 반환
        ArticleResponseDto articleResponseDto = new ArticleResponseDto(article, commentDtoList);
        return articleResponseDto;
    }

    // 검색
    @Transactional
    public List<ArticleListResponseDto> getSearchArticles(String keyword) {
        List<Article> searchedArticles = articleRepository.findByTitleContaining(keyword);

        List<ArticleListResponseDto> responseDto = searchedArticles.stream()
                .map(article -> new ArticleListResponseDto(article, commentRepository.findAllByArticleOrderByCreatedAtDesc(article)))
                .collect(Collectors.toList());
        return responseDto;
    }

}
