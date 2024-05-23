package com.example.myBlog.service;

import com.example.myBlog.entity.Article;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


public interface ArticleService {

     List<Article> findAll();
     Optional<Article> findById(Long id);
     void save(Article article);
     void deleteById(Long id);
     Article createArticle(Article article);
}
