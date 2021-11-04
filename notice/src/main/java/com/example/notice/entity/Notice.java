package com.example.notice.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notice_id")
    private Integer id;
    private String content;
    private Timestamp from_date;
    private Timestamp to_date;
    private Integer cat_id;
}
