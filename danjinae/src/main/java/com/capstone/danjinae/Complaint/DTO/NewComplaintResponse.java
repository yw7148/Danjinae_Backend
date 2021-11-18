package com.capstone.danjinae.Complaint.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class NewComplaintResponse {
    private Integer id;
    private String content;
    private Integer userId;
    private Integer aptId;
}
