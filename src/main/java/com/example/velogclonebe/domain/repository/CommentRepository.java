package com.example.velogclonebe.domain.repository;

import com.example.velogclonebe.domain.entity.Article;
import com.example.velogclonebe.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByArticle(Article article);
    List<Comment> findAllByArticleOrderByCreatedAtDesc(Article article);
}
