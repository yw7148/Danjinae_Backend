package com.capstone.danjinae.post.service;

import com.capstone.danjinae.post.entity.Comment;
import com.capstone.danjinae.post.entity.NestedComment;
import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.repository.CommentRepository;
import com.capstone.danjinae.post.repository.NestedCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NestedCommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    NestedCommentRepository nestedCommentRepository;


    @Transactional
    public NestedComment write(Integer commentId, NestedComment ncomment){
        Optional<Comment> comment= commentRepository.findById(commentId);
        ncomment.setComment(comment.get());
        nestedCommentRepository.save(ncomment);
        return ncomment;
    }

}
