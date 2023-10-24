package com.capstone.danjinae.post.service;

import com.capstone.danjinae.post.entity.Comment;
import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.repository.CommentRepository;
import com.capstone.danjinae.post.repository.PostRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @Transactional
    public Page<Comment> getComment(Integer postId, Pageable pageable){
        Post post = postRepository.getById(postId);
        return commentRepository.findCommentsByPost(post ,pageable);
    }

    @Transactional
    public Comment write(Integer postId, Comment comment){
        Optional<Post> post= postRepository.findById(postId);
        comment.setPost(post.get());
        commentRepository.save(comment);
        return comment;
    }



}
