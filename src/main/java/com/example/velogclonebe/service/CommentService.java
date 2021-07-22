package com.example.velogclonebe.service;

import com.example.velogclonebe.domain.dto.request.CommentCreateRequestDto;
import com.example.velogclonebe.domain.dto.request.CommentUpdateRequestDto;
import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.domain.entity.Comment;
import com.example.velogclonebe.domain.repository.ArticleRepository;
import com.example.velogclonebe.domain.repository.CommentRepository;
import com.example.velogclonebe.domain.repository.UserRepository;
import com.example.velogclonebe.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;


    // 댓글 작성
    @Transactional
    public Comment createComment(CommentCreateRequestDto requestDto, Long articleId, String username) {

        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new ApiRequestException("해당 게시글이 존재하지 않습니다.")
        );

        String profileUrl = userRepository.findByUsername(username).getProfileUrl();

        Comment comment = new Comment(requestDto, article, username, profileUrl);
        commentRepository.save(comment);
        return comment;
    }

    // 댓글 수정
    @Transactional
    public Long updateComment(Long commentId, CommentUpdateRequestDto requestDto, String username) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiRequestException("해당 댓글이 존재하지 않습니다.")
        );

        if (!comment.getUsername().equals(username)) {
            throw new ApiRequestException("수정 권한이 없습니다.");
        }

        comment.update(requestDto);
        return comment.getCommentId();
    }

    @Transactional
    public void deleteComment(Long commentId, String username) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiRequestException("해당 댓글이 존재하지 않습니다.")
        );

        if (!comment.getUsername().equals(username)) {
            throw new ApiRequestException("삭제 권한이 없습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
