package com.example.velogclonebe.controller;

import com.example.velogclonebe.domain.dto.request.CommentCreateRequestDto;
import com.example.velogclonebe.domain.dto.request.CommentUpdateRequestDto;
import com.example.velogclonebe.domain.entity.Comment;
import com.example.velogclonebe.service.CommentService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성 요청
    @PostMapping("/api/comment/{articleId}")
    public Comment createComment(@PathVariable Long articleId, @RequestBody CommentCreateRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return commentService.createComment(requestDto, articleId, username);
    }


    //댓글 수정 요청
    @PutMapping("/api/comment/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        commentService.updateComment(commentId, requestDto, username);

    }

    // 댓글 삭제 요청
    @DeleteMapping("/api/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        commentService.deleteComment(commentId, username);
    }


}

