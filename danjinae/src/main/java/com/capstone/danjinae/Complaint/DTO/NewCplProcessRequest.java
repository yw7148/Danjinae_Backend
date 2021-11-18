package com.capstone.danjinae.Complaint.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCplProcessRequest {
    private Integer cplId;
    private Integer mgrId;
    private String content;
}
