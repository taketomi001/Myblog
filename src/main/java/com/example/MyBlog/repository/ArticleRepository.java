package com.example.MyBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyBlog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
