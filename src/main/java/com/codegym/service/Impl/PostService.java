package com.codegym.service.Impl;

import com.codegym.dto.PostDTO;
import com.codegym.model.Post;
import com.codegym.repository.IPostRepository;
import com.codegym.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    IPostRepository postRepository;

    @Override
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Post>> getPostWithPagination(int page) {
        Pageable pageable = PageRequest.of(page,
                5);
        Page<Post> postList = postRepository.findAll(pageable);

        if (postList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postList.getContent(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> getPostById(int id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(postOptional.get(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> save(PostDTO newPost) {
        Post post = new Post();
        BeanUtils.copyProperties(newPost,
                post);

        //Get the account's id to set in to Post object
        /*{
            postDTO.getAccountId();
        }*/

        Post savedPost = postRepository.save(post);
        if (savedPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(savedPost,
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Post> remove(int id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postRepository.deleteById(id);
        return new ResponseEntity<>(postOptional.get(),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> update(int id,
                                       PostDTO updatePost) {
        Post currentPost = postRepository
                .findById(id)
                .get();

        BeanUtils.copyProperties(updatePost,
                currentPost);

        Post savedPost = postRepository.save(currentPost);
        if (savedPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(savedPost,
                HttpStatus.CREATED);
    }
}