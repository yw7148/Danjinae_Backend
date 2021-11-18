package com.capstone.danjinae.post.DTO.postDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {

    private Integer postId;
    private String title;
    private String content;
    private Integer userId;
    private Integer aptId;
}
