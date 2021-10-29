package com.example.post.repository;

import com.example.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    //findBy(컬럼이름)Containing
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}

