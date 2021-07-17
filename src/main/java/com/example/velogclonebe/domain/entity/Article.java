package com.example.velogclonebe.domain.entity;


import com.example.velogclonebe.domain.dto.request.ArticleRequestDto;
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

    // @Column(nullable = false)
    // private Long userId;

    public Article(ArticleRequestDto articleRequestDto) {
        this.title = articleRequestDto.getTitle();
        this.text = articleRequestDto.getText();
        this.textHtml = articleRequestDto.getTextHtml();
        this.textMarkdown = articleRequestDto.getTextMarkdown();
        this.image = articleRequestDto.getImage();
        // this.userId = articleRequestDto.getUserId();
    }

    public void update(ArticleRequestDto articleRequestDto) {
        this.title = articleRequestDto.getTitle();
        this.text = articleRequestDto.getText();
        this.textHtml = articleRequestDto.getTextHtml();
        this.textMarkdown = articleRequestDto.getTextMarkdown();
        this.image = articleRequestDto.getImage();
    }


}
