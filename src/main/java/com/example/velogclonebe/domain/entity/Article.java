package com.example.velogclonebe.domain.entity;


import com.example.velogclonebe.domain.dto.request.ArticleRequestDto;
import com.example.velogclonebe.domain.dto.request.ArticleUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Article extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long articleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String textHtml;

    @Column(nullable = false)
    private String textMarkdown;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long countComment;


    public Article(ArticleRequestDto articleRequestDto, String username) {
        this.title = articleRequestDto.getTitle();
        this.text = articleRequestDto.getText();
        this.textHtml = articleRequestDto.getTextHtml();
        this.textMarkdown = articleRequestDto.getTextMarkdown();
        this.image = articleRequestDto.getImage();
        this.username = username;
        this.countComment = 0L;
    }

    public void update(ArticleUpdateRequestDto articleRequestDto) {
        this.title = articleRequestDto.getTitle();
        this.text = articleRequestDto.getText();
        this.textHtml = articleRequestDto.getTextHtml();
        this.textMarkdown = articleRequestDto.getTextMarkdown();
    }


}
