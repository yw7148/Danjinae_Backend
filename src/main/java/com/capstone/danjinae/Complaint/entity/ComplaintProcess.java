package com.capstone.danjinae.Complaint.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import javax.persistence.*;

//table
@Entity
@Getter
@Table(name = "complaintProcess")
@NoArgsConstructor
public class ComplaintProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cplprocessid", columnDefinition = "INT", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "cplid")
    private Integer cplId;

    @Column(name = "mgrid")
    private Integer mgrId;

    @Column(name = "state")
    private String state;

    @Builder
    public ComplaintProcess(String content, Integer cplId, Integer mgrId, String state) {
        this.content = content;
        this.cplId = cplId;
        this.mgrId = mgrId;
        this.state = state;
    }
}
