package com.example.velogclonebe.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleUpdateRequestDto {
    private String title;
    private String text;
    private String textHtml;
    private String textMarkdown;
}