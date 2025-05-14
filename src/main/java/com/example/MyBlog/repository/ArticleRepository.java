package com.example.MyBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyBlog.model.Article;

import java.time.LocalDateTime;
import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);

    List<Article> findByCreatedAtAfter(LocalDateTime date);

    List<Article> findTop5ByOrderByCreatedAtDesc();
}
