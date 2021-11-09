package com.capstone.danjinae.Complaint.entity;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

import javax.persistence.*;

//table
@Entity
@Getter
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaintid", columnDefinition = "INT", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "userid")
    private Integer userId;
    @Column(name = "aptid")
    private Integer aptId;

    @Builder
    public Complaint(String content, Integer userId, Integer aptId, Integer catId) {
        this.content = content;
        this.userId = userId;
        this.aptId = aptId;
    }
}
