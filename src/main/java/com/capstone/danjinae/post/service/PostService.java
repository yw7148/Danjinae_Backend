package com.capstone.danjinae.post.service;

import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Post write(Post post){

        postRepository.save(post);
        return post;
    }

    @Transactional
    public Page<Post> totalPostList(Integer aptId, Pageable pageable){

        return postRepository.findByAptId(aptId, pageable);
    }

    @Transactional
    public Post getPost(Integer postId){

        return postRepository.findById(postId).get();
    }

    @Transactional
    public void deletePost(Integer postId){

        postRepository.deleteById(postId);

    }

    @Transactional
    public Page<Post> searchKeyword(Integer aptId,String keyword,Pageable pageable){

        return postRepository.findByAptIdAndTitleContaining(aptId, keyword, pageable);
    }
}
