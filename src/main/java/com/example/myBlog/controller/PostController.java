package com.example.myBlog.controller;

import com.example.myBlog.entity.Post;
import com.example.myBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> Post = postService.findById(id);
        return Post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Optional<Post> postOptional = postService.findById(id);
        if (postOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Post Post = postOptional.get();
        Post.setTitle(updatedPost.getTitle());
        Post.setContent(updatedPost.getContent());
        Post.setPublishedDate(updatedPost.getPublishedDate());
        Post.setTags(updatedPost.getTags());
        postService.save(Post);
        return ResponseEntity.ok(Post);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
