package com.capstone.danjinae.MgFee.DTO;

import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@Setter
public class UserMgFeeResponse {
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer fee;
    private Boolean paid;

    private Integer userId;
    private Integer aptId;
    private Integer catId;

}
