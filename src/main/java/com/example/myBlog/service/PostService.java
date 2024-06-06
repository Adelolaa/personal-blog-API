package com.example.myBlog.service;

import com.example.myBlog.entity.Post;



import java.util.List;
import java.util.Optional;


public interface PostService {

     List<Post> findAll();
     Optional<Post> findById(Long id);
     void save(Post post);
     void deleteById(Long id);
     Post createPost(Post post);

}
