package com.capstone.danjinae.post.controller;

import com.capstone.danjinae.post.entity.Comment;
import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.repository.PostRepository;
import com.capstone.danjinae.post.service.CommentService;
import com.capstone.danjinae.post.service.PostService;
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

    //댓글 list show
    @GetMapping("/list")
    public List<Comment> list(@RequestParam("postId") Integer postId){
        Post post= postService.getPost(postId);
        return commentService.getComment(post);
    }

    //댓글 작성
    @PostMapping("/comment")
    private Comment comment(@RequestBody Comment comment){

        System.out.println("5");
        return commentService.write(comment);
    }

}
