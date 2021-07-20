package com.example.velogclonebe.domain.repository;

import com.example.velogclonebe.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 리스트 조회
    List<Article> findAllByOrderByCreatedAtDesc();

    // 리스트 조회 + 댓글수
//    @Query(value = "SELECT a.*,  COUNT(c.comment_id) countComment FROM article a LEFT OUTER JOIN comment c ON a.article_id = c.article_id WHERE a.article_id > 0 GROUP BY a.article_id ORDER BY a.article_id DESC", nativeQuery = true)
//    List<Article> findAllAndCountComment();

    // 검색
    List<Article> findByTitleContaining(String keyword);

}
