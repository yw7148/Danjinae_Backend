package com.capstone.danjinae.notice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notice_id")
    private Integer id;

    @Column(name="aptId")
    private Integer aptId;

    @Column(name = "content")
    private String content;

    @Column(name="start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;

    @Column(name="cat_id")
    private Integer catId;

    @Builder
    public Notice(String content, Integer aptId,Timestamp startDate, Timestamp endDate, Integer catId) {
        this.aptId = aptId;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.catId = catId;
    }
}
