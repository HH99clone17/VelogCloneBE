package com.example.velogclonebe.domain.dto.response;

import com.example.velogclonebe.domain.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentGetResponseDto {
    private Long commentId;
    private String commentText;
    private Long userId;

    public CommentGetResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.commentText = comment.getCommentText();
        this.userId = comment.getUserId();
    }
}
