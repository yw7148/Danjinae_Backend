package com.capstone.danjinae.post.repository;

import com.capstone.danjinae.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    //findBy(컬럼이름)Containing
    Page<Post> findByAptIdAndTitleContaining(Integer aptId,String keyword, Pageable pageable);

    Page<Post> findByAptId(Integer aptId, Pageable pageable);
}

