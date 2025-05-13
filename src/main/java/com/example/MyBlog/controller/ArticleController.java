package com.example.MyBlog.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyBlog.model.Article;
import com.example.MyBlog.repository.ArticleRepository;

@RestController

@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;


    public ArticleController(ArticleRepository articleRepository) {

        this.articleRepository = articleRepository;

    }

    @GetMapping
public ResponseEntity<List<Article>> getAllArticles() {
    List<Article> articles = articleRepository.findAll();
    if (articles.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(articles);
}

@GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping
public ResponseEntity<Article> createArticle(@RequestBody Article article) {
    article.setCreatedAt(LocalDateTime.now());
    article.setUpdatedAt(LocalDateTime.now());
    Article savedArticle = articleRepository.save(article);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
}

@PutMapping("/{id}")
public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {

    Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

    article.setTitle(articleDetails.getTitle());
    article.setContent(articleDetails.getContent());
    article.setUpdatedAt(LocalDateTime.now());

    Article updatedArticle = articleRepository.save(article);
    return ResponseEntity.ok(updatedArticle);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {

    Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }

    articleRepository.delete(article);
    return ResponseEntity.noContent().build();
}

}
