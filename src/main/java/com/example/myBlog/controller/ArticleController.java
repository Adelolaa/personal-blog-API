package com.example.myBlog.controller;

import com.example.myBlog.entity.Article;
import com.example.myBlog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Optional<Article> article = articleService.findById(id);
        return article.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {
        Optional<Article> articleOptional = articleService.findById(id);
        if (articleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Article article = articleOptional.get();
        article.setTitle(updatedArticle.getTitle());
        article.setContent(updatedArticle.getContent());
        article.setPublishedDate(updatedArticle.getPublishedDate());
        article.setTags(updatedArticle.getTags());
        articleService.save(article);
        return ResponseEntity.ok(article);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        articleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
