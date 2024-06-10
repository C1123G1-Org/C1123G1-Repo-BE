package com.codegym.service;

import com.codegym.dto.PostDTO;
import com.codegym.model.Cote;
import com.codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPostService {
    ResponseEntity<List<Post>> getAllPost();

    ResponseEntity<List<Post>> getPostWithPagination(int page);

    ResponseEntity<Post> getPostById(int id);

    ResponseEntity<Post> save(PostDTO newPost);

    ResponseEntity<Post> remove(int id);

    ResponseEntity<Post> update(int id,
                                PostDTO updatePost);

    ResponseEntity<Page<Post>> getPostWithPageAndStatus (int page, String status);
}
