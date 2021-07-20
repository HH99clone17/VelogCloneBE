package com.example.velogclonebe.controller;


import com.example.velogclonebe.domain.dto.request.ArticleRequestDto;
import com.example.velogclonebe.domain.dto.response.ArticleResponseDto;
import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.service.ArticleService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    // 게시글 리스트 조회
    @GetMapping("/api/article")
    public List<Article> getArticle() {
        return articleService.getArticles();
    }

    // 게시글 작성
    @PostMapping("/api/article")
    public void setArticle(@RequestBody ArticleRequestDto articleRequestDto, @AuthenticationPrincipal UserDetails userDetails) {

        // System.out.println(userDetails.getUsername());
        String username = userDetails.getUsername();
        articleService.setArticle(articleRequestDto, username);
    }

    // 게시글 수정
    @PutMapping("/api/article/{articleId}")
    public void updateArticle(@PathVariable Long articleId, @RequestBody ArticleRequestDto articleRequestDto, @AuthenticationPrincipal UserDetails userDetails) {
        articleService.updateArticle(articleId, articleRequestDto, userDetails.getUsername());
    }

    // 게시글 삭제
    @DeleteMapping("/api/article/{articleId}")
    public void deleteArticle(@PathVariable Long articldId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        articleService.deleteArticle(articldId, username);
    }

    // 게시글 상세페이지 요청
    @GetMapping("/api/article/{articleId}")
    public ArticleResponseDto getArticleDetail(@PathVariable Long articleId) {
        return articleService.getArticleDetail(articleId);
    }

    // 검색
    @GetMapping("/api/search")
    public List<Article> getSeachedArticles(@RequestParam String keyword) {
        return articleService.getSearchArticles(keyword);
    }


}
