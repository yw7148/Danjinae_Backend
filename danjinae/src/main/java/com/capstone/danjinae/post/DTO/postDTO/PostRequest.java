package com.capstone.danjinae.post.DTO.postDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class PostRequest {

    private String title;
    private String content;
    private Integer userId;
    private Integer aptId;
}
