package com.capstone.danjinae.post.controller;

import com.capstone.danjinae.post.DTO.commentDTO.CommentRequest;
import com.capstone.danjinae.post.DTO.commentDTO.CommentResponse;
import com.capstone.danjinae.post.DTO.nestedcommentDTO.NestedCommentRequest;
import com.capstone.danjinae.post.DTO.postDTO.PostRequest;
import com.capstone.danjinae.post.DTO.postDTO.PostResponse;
import com.capstone.danjinae.post.entity.Comment;
import com.capstone.danjinae.post.entity.NestedComment;
import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.post.service.CommentService;
import com.capstone.danjinae.post.service.NestedCommentService;
import com.capstone.danjinae.post.service.PostService;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/nestedcomment")
public class NestedCommentController {

    @Autowired
    private NestedCommentService nestedcommentService;

    @Autowired
    private UserService userService;

    // 대댓글 작성
    @PostMapping("/add")
    private Boolean nestedcomment(Principal user, @RequestBody NestedCommentRequest ncomment,
            @RequestParam("commentId") Integer commentId) {

        NestedComment toadd;
        try {
            User aptUser = userService.UserInfoWithPhone(user.getName());
            ncomment.setNuserId(aptUser.getId());

            toadd = NestedComment.builder().nuserId(ncomment.getNuserId()).ncomment(ncomment.getNcomment()).build();
            nestedcommentService.write(commentId, toadd);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
