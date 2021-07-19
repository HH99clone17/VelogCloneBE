package com.example.velogclonebe.domain.dto.response;


import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.domain.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleListResponseDto {

    private Long articleId;
        private String title;
        private String text;
        private String textHtml;
        private String textMarkdown;

    // public ArticleListResponseDto(Article article, List<Comment> commentList) {
    public ArticleListResponseDto(Article article) {
        this.articleId = article.getArticleId();
        this.title = article.getTitle();
        this.text = article.getText();
        this.textHtml = article.getTextHtml();
        this.textMarkdown = article.getTextMarkdown();
    }
}
