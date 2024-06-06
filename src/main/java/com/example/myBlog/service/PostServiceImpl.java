package com.example.myBlog.service;


import com.example.myBlog.entity.Post;
import com.example.myBlog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post article) {
        postRepository.save(article);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);

    }

    @Override
    public Post createPost(Post post) {
        return null;
    }




}
