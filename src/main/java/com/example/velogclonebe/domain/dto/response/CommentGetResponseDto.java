package com.example.velogclonebe.domain.dto.response;

import com.example.velogclonebe.domain.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class CommentGetResponseDto {
    private Long commentId;
    private String commentText;
    private String username;
    private LocalDateTime createdAt;
    private String profileUrl;

    public CommentGetResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.commentText = comment.getCommentText();
        this.username = comment.getUsername();
        this.createdAt = comment.getCreatedAt();
        this.profileUrl = comment.getProfileUrl();
    }
}
