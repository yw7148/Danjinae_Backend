package com.capstone.danjinae.notice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
public class NoticeReadHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="historyId")
    private Integer id;
    @Column(name = "userId")
    private Integer userId;

    @Column(name="noticeId")
    private Integer noticeId;

    @Builder
    public NoticeReadHistory(Integer userId, Integer noticeId) {
        this.userId = userId;
        this.noticeId = noticeId;
    }
}
