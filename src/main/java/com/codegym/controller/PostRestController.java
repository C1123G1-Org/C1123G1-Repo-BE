package com.codegym.controller;

import com.codegym.dto.PostDTO;
import com.codegym.model.Post;
import com.codegym.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class PostRestController {
    @Autowired
    IPostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPost() {
        return postService.getAllPost("Hiển thị");
    }

    @GetMapping("/list-post/{page}")
    public ResponseEntity<List<Post>> getPostWithPagination(@PathVariable Integer page) {
        return postService.getPostWithPagination(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> savePost(@RequestBody PostDTO postDTO) {
        return postService.save(postDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable int id) {
        return postService.remove(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id,
                                           @RequestBody PostDTO postDTO) {
        return postService.update(id,
                postDTO);
    }

    // Bình
    @GetMapping("/mgt/{page}")
    public ResponseEntity<Page<Post>> getPostWithPageAndStatus(@PathVariable int page,
                                                               @RequestParam(value = "status") String status) {
        return postService.getPostWithPageAndStatus(page,
                status);
    }

    @GetMapping("/focal-point")
    public ResponseEntity<Post> getFocalPointPost() {
        return postService.getFocalPointPost();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> getSearchPosts(@RequestParam String word) {
        System.out.println(word);
        return postService.getSearchPosts(word);
    }
}