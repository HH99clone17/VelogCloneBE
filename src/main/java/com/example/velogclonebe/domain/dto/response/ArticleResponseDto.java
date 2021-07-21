package com.example.velogclonebe.domain.dto.response;


import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.domain.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArticleResponseDto {

    private Long articleId;
    private String title;
    private String text;
    private String textHtml;
    private String textMarkdown;
    private String username;
    private LocalDateTime createdAt;
    private List<CommentGetResponseDto> commentList;

    public ArticleResponseDto(Article article, List<CommentGetResponseDto> commentList) {
        this.articleId = article.getArticleId();
        this.title = article.getTitle();
        this.text = article.getText();
        this.textHtml = article.getTextHtml();
        this.textMarkdown = article.getTextMarkdown();
        this.username = article.getUsername();
        this.createdAt = article.getCreatedAt();
        this.commentList = commentList;
    }

}
