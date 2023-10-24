package com.capstone.danjinae.post.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//table
@Entity
@Getter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content, Integer userId, Integer aptId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.aptId = aptId;
    }

}
