package com.example.velogclonebe.domain.dto.response;


import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.domain.entity.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleResponseDto {

    private Long articleId;
    private String title;
    private String text;
    private String textHtml;
    private String textMarkdown;

}
