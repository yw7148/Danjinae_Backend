package com.capstone.danjinae.post.entity;

import com.capstone.danjinae.post.service.PostService;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public Comment(Integer commentId, Integer userId, String comment, Integer postId) {

        PostService postService = null;
        this.commentId= commentId;
        this.userId= userId;
        this.comment= comment;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
