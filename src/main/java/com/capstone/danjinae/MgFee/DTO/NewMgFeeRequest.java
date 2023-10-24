package com.capstone.danjinae.MgFee.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMgFeeRequest {
    private Integer fee;
    private String address;
    private Integer aptId;
    private Integer catId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
