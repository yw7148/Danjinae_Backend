package com.capstone.danjinae.MgFee.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

//table
@Entity
@Getter
@Table(name = "mgfee")
@NoArgsConstructor
public class MgFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mgfee_id", columnDefinition = "INT", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "date")
    private Timestamp date;
    @Column(name = "fee")
    private Integer fee;
    @Column(name = "paid")
    private Boolean paid;

    @Column(name = "content")
    private String content;

    @Column(name = "userid")
    private Integer userId;
    @Column(name = "aptid")
    private Integer aptId;
    @Column(name = "catid")
    private Integer catId;

    @Builder
    public MgFee(Integer fee, Integer userId, Integer aptId, Integer catId, String content, Date date) {
        this.content = content;
        this.fee = fee;
        this.userId = userId;
        this.aptId = aptId;
        this.catId = catId;
        this.date = new Timestamp(date.getTime());
    }

    public void paid() {
        this.paid = true;
    }
}
