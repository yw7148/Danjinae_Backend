package com.capstone.danjinae.post.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer commentId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

}
