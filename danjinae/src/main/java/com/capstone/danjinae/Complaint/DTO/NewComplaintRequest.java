package com.capstone.danjinae.Complaint.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewComplaintRequest {
    private String content;
    private Integer userId;
    private Integer aptId;
}
