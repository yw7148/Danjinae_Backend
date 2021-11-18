package com.capstone.danjinae.post.DTO.commentDTO;

import com.capstone.danjinae.post.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommentResponse {

    private Integer commentId;
    private Integer userId;
    private String comment;
    private Integer postId;
}
