package com.example.post.controller;

import com.example.post.entity.Comment;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import com.example.post.service.CommentService;
import com.example.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    //댓글 show
    @GetMapping("/getcomment/{id}")
    public List<Comment> getComment(@PathVariable Integer id){
        Post post= postService.getpost(id);
        return commentService.getComment(post);
    }

    //댓글 작성
    @PutMapping("/commentwrite/{id}")
    private Comment commentWrite(@PathVariable Integer id, @RequestBody Comment comment){
        return commentService.commentWrite(comment,id);
    }

}
