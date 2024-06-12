package com.codegym.service.Impl;

import com.codegym.dto.PostDTO;
import com.codegym.model.Account;
import com.codegym.model.Post;
import com.codegym.repository.IPostRepository;
import com.codegym.service.IAccountService;
import com.codegym.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    IPostRepository postRepository;
    @Autowired
    private IAccountService accountService;

    @Override
    public ResponseEntity<List<Post>> getAllPost(String status) {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC,
                "postDate"));
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Post>> getPostWithPagination(int page) {
        /*Pageable pageable = PageRequest.of(page,
                5);*/
        Pageable pageable = PageRequest.of(page,
                5,
                Sort.by(Sort.Direction.DESC,
                        "postDate"));
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
        Account account = accountService.findById(newPost.getAccountId());
        Post post = new Post();
        BeanUtils.copyProperties(newPost,
                post);
        post.setAccount(account);
        //Get the account's id to set in to Post object
        /*{
            postDTO.getAccountId();
        }*/
        if (post.isFocalPoint()) {
            Post post2 = postRepository.findFirstByIsFocalPointTrueOrderByPostDateDesc();
            if (post2 != null) {
                post2.setFocalPoint(false);
                postRepository.save(post2);
            }
        }
        try {
            postRepository.save(post);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
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
        System.out.println(updatePost);
        Post currentPost = postRepository
                .findById(id)
                .get();

        BeanUtils.copyProperties(updatePost,
                currentPost);
        if (currentPost.isFocalPoint()) {
            Post post = postRepository.findFirstByIsFocalPointTrueOrderByPostDateDesc();
            if (post != null) {
                if (post.getId() != currentPost.getId()){
                    post.setFocalPoint(false);
                    postRepository.save(post);
                }

            }
        }
        Post savedPost = postRepository.save(currentPost);
        if (savedPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(savedPost,
                HttpStatus.CREATED);
    }

    // Bình
    @Override
    public ResponseEntity<Page<Post>> getPostWithPageAndStatus(int page,
                                                               String status) {
        Pageable pageable = PageRequest.of(page,
                5,
                Sort
                        .by("postDate")
                        .descending());
        Page<Post> list = postRepository.findPostsByStatusContaining(pageable,
                status);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> getFocalPointPost() {
        return new ResponseEntity<>(postRepository.findFirstByIsFocalPointTrueOrderByPostDateDesc(),
                HttpStatus.OK);
    }
}