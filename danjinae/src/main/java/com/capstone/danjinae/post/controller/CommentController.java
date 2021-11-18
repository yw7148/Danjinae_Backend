package com.capstone.danjinae.post.controller;

import com.capstone.danjinae.post.DTO.commentDTO.CommentRequest;
import com.capstone.danjinae.post.DTO.commentDTO.CommentResponse;
import com.capstone.danjinae.post.DTO.postDTO.PostRequest;
import com.capstone.danjinae.post.DTO.postDTO.PostResponse;
import com.capstone.danjinae.post.entity.Comment;
import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.service.CommentService;
import com.capstone.danjinae.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    //댓글 list show
    @GetMapping("/list")
    public Page<CommentResponse> list(@PageableDefault(page=0,size=10,sort="commentId",direction= Sort.Direction.DESC) Pageable pageable,@RequestParam("postId") Integer postId) {
        Page<CommentResponse> dtoList;
        Page<Comment> list= null;

        list= commentService.getComment(pageable);

        dtoList = list.map(new Function< Comment, CommentResponse>(){

            @Override
            public CommentResponse apply(Comment entity) {

                CommentResponse dto = new CommentResponse();
                dto.setCommentId(entity.getCommentId());
                dto.setUserId(entity.getUserId());
                dto.setComment(entity.getComment());

                return dto;
            }
        });
        return dtoList;
    }


    //댓글 작성
    @PostMapping("/add")
    private Boolean comment(@RequestBody CommentRequest comment, @RequestParam("postId") Integer postId) {

        Comment toadd;
        try {
            toadd = Comment.builder().userId(comment.getUserId()).comment(comment.getComment()).build();
            commentService.write(postId, toadd);

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

