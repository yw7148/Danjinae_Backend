package com.example.post.service;

import com.example.post.entity.Comment;
import com.example.post.entity.Post;
import com.example.post.repository.CommentRepository;
import com.example.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Comment> getComment(Post post){
        return commentRepository.findCommentsByPost(post);
    }

    public Comment commentWrite(Comment comment, Integer post_id){
        Optional<Post> post= postRepository.findById(post_id);
        comment.setPost(post.get());
        commentRepository.save(comment);
        return comment;
    }

}
