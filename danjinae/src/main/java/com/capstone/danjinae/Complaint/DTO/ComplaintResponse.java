package com.capstone.danjinae.Complaint.DTO;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintResponse {
    private Integer cplId;
    private String content;
    private Page<String> processes;
}
