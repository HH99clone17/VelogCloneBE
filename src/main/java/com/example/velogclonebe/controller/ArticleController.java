package com.example.velogclonebe.controller;


import com.example.velogclonebe.domain.dto.request.ArticleRequestDto;
import com.example.velogclonebe.domain.dto.request.ArticleUpdateRequestDto;
import com.example.velogclonebe.domain.dto.response.ArticleListResponseDto;
import com.example.velogclonebe.domain.dto.response.ArticleResponseDto;
import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.service.ArticleService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    // 게시글 리스트 조회
    @GetMapping("/api/article")
    public List<ArticleListResponseDto> getArticle() {
        return articleService.getArticles();
    }

    // 게시글 작성
    @PostMapping("/api/article")
    public void setArticle(
                @RequestParam("title") String title
                , @RequestParam("text") String text
                , @RequestParam("textHtml") String textHtml
                , @RequestParam(value="textMarkdown", required = false) String textMarkdown
                , @RequestParam(value="image",required = false) MultipartFile file
                , @AuthenticationPrincipal UserDetails userDetails
            ) throws IOException {


        String username = userDetails.getUsername();
        // articleService.setArticle(articleRequestDto, file, username);
        articleService.setArticle(title, text, textHtml, textMarkdown, file, username);
    }

    // 게시글 수정
    @PutMapping("/api/article/{articleId}")
    public void updateArticle(@PathVariable Long articleId, @RequestBody ArticleUpdateRequestDto articleUpdateRequestDto, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        articleService.updateArticle(articleId, articleUpdateRequestDto, username);
    }

    // 게시글 삭제
    @DeleteMapping("/api/article/{articleId}")
    public void deleteArticle(@PathVariable Long articleId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        articleService.deleteArticle(articleId, username);
    }

    // 게시글 상세페이지 요청
    @GetMapping("/api/article/{articleId}")
    public ArticleResponseDto getArticleDetail(@PathVariable Long articleId) {
        return articleService.getArticleDetail(articleId);
    }

    // 검색
    @GetMapping("/api/search")
    public List<ArticleListResponseDto> getSeachedArticles(@RequestParam String keyword) {
        return articleService.getSearchArticles(keyword);
    }


}
