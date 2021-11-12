package com.capstone.danjinae.post.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

//table
@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id",columnDefinition="INT",insertable=false, updatable=false)
    private Integer postId;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="apt_id")
    private Integer aptId;

}
