package com.example.post.entity;

import lombok.Data;

import javax.persistence.*;

//table
@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id",columnDefinition="INT",insertable=false, updatable=false)
    private Integer id;
    private String title;
    private String content;
    private Integer user_id;
    private Integer apt_id;

}
