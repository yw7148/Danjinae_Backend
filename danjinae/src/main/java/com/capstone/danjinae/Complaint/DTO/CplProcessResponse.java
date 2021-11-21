package com.capstone.danjinae.Complaint.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CplProcessResponse {
    private Integer mgrId;
    private String state;
    private String content;
}
