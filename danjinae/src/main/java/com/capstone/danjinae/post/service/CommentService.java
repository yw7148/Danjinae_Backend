package com.capstone.danjinae.post.service;

import com.capstone.danjinae.post.entity.Comment;
import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.repository.CommentRepository;
import com.capstone.danjinae.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Comment> getComment(Post post){
        return commentRepository.findCommentsByPost(post);
    }

    @Transactional
    public Comment write(Comment comment){
        System.out.println("1");
        Optional<Post> post= postRepository.findById(comment.getPost().getPostId());
        System.out.println("2");
        comment.setPost(post.get());
        System.out.println("3");
        commentRepository.save(comment);
        System.out.println("4");
        return comment;
    }

}
