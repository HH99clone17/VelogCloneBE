package com.example.velogclonebe.controller;


import com.example.velogclonebe.domain.dto.request.ArticleRequestDto;
import com.example.velogclonebe.domain.dto.response.ArticleResponseDto;
import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.service.ArticleService;

import lombok.RequiredArgsConstructor;
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
    public List<Article> getArticle() {
        return articleService.getArticles();
    }

    // 게시글 작성
    @PostMapping("/api/article")
    public void setArticle(@RequestBody ArticleRequestDto articleRequestDto, @RequestParam("file") MultipartFile file) throws IOException {
        articleService.setArticle(articleRequestDto, file);
    }

    // 게시글 수정
    @PutMapping("/api/article/{articleId}")
    public void updateArticle(@PathVariable Long articleId, @RequestBody ArticleRequestDto articleRequestDto) {
        articleService.updateArticle(articleId, articleRequestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/article/{articleId}")
    public void deleteArticle(@PathVariable Long articldId, @RequestBody ArticleRequestDto articleRequestDto) {
        articleService.deleteArticle(articldId, articleRequestDto);
    }

    // 게시글 상세페이지 요청
    @GetMapping("/api/article/{articleId}")
    public ArticleResponseDto getArticleDetail(@PathVariable Long articleId){
        return articleService.getArticleDetail(articleId);
    }


}
