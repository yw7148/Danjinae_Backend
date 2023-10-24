package com.capstone.danjinae.post.entity;

import com.capstone.danjinae.post.service.CommentService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class NestedComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ncomment_id")
    private Integer ncommentId;

    @Column(name="nuser_id")
    private Integer nuserId;

    @Column(name="ncomment")
    private String ncomment;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    @Builder
    public NestedComment(Integer ncommentId, Integer nuserId, String ncomment, Integer commentId) {

        CommentService commentService = null;
        this.ncommentId= ncommentId;
        this.nuserId= nuserId;
        this.ncomment= ncomment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

}
