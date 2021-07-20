package com.example.velogclonebe.domain.entity;

import com.example.velogclonebe.domain.dto.request.CommentCreateRequestDto;
import com.example.velogclonebe.domain.dto.request.CommentUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false)
    private String commentText;

    @Column(nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)  // 두번 조회를 한다
    @JoinColumn(name = "articleId")
    private Article article;


    public Comment(CommentCreateRequestDto requestDto, Article article, String username){

        this.commentText = requestDto.getCommentText();
        this.article = article;
        this.username = username;

    }

    public void update(CommentUpdateRequestDto requestDto) {
        this.commentText = requestDto.getCommentText();
    }



}
