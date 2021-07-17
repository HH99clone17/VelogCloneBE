package com.example.velogclonebe.service;


import com.example.velogclonebe.domain.dto.request.ArticleRequestDto;
import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.domain.repository.ArticleRepository;
import com.example.velogclonebe.domain.repository.CommentRepository;
import com.example.velogclonebe.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 게시글 리스트 조회
    @Transactional
    public List<Article> getArticles() {
        List<Article> articleList = articleRepository.findAllByOrderByCreatedAtDesc();
        return articleList;
    }

    // 게시글 작성
    @Transactional
    public void setArticle(ArticleRequestDto articleRequestDto) {
        Article article = new Article(articleRequestDto);
        articleRepository.save(article);
    }

    // 게시글 수정
    @Transactional
    public void updateArticle(Long articleId, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                ()-> new ApiRequestException("해당 글이 존재하지 않습니다."));
        article.update(articleRequestDto);
    }

    // 게시글 삭제
    @Transactional
    public void deleteArticle(Long articleId, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                ()->new ApiRequestException("해당 글이 존재하지 않습니다."));

        // 글 삭제시 해당 글에 연관된 댓글들 삭제 (commentRepository 미반영)
        // commentRepository.deleteAllByArticle(article);

        articleRepository.deleteById(articleId);
    }
    

}
