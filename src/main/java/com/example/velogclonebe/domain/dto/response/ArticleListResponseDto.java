package com.example.velogclonebe.domain.dto.response;


import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.domain.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArticleListResponseDto {

    private Long articleId;
    private String title;
    private String text;
    private String textHtml;
    private String textMarkdown;
    private String imageUrl;
    private int countComment;
    private LocalDateTime createdAt;
    private String username;



    public ArticleListResponseDto(Article article, List<Comment> commentList) {
        this.articleId = article.getArticleId();
        this.title = article.getTitle();
        this.text = article.getText();
        this.textHtml = article.getTextHtml();
        this.textMarkdown = article.getTextMarkdown();
        this.countComment = commentList.size();
        this.imageUrl = article.getImageUrl();
        this.username = article.getUsername();
        this.createdAt = article.getCreatedAt();

    }
}
