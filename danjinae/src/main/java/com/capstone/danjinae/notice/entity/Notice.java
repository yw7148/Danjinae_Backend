package com.capstone.danjinae.notice.entity;

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
    @Column(name = "content")
    private String content;

    @Column(name="start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp enddate;

    @Column(name="cat_id")
    private Integer catId;
}
