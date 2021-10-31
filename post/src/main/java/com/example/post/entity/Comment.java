package com.example.post.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Integer id;
    private Integer user_id;
    private String comment;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

}
