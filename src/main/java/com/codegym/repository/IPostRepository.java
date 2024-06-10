package com.codegym.repository;

import com.codegym.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findPostsByStatusContaining(Pageable pageable,
                                           String status);

    Post findFirstByIsFocalPointTrueOrderByPostDateDesc();
}
