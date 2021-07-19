package com.example.velogclonebe.domain.repository;

import com.example.velogclonebe.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
