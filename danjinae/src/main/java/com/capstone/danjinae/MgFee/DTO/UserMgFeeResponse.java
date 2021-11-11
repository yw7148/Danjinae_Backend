package com.capstone.danjinae.MgFee.DTO;

import lombok.Setter;

import java.sql.Timestamp;

import lombok.Getter;

@Getter
@Setter
public class UserMgFeeResponse {
    private Integer id;

    private Timestamp date;
    private Integer fee;
    private Boolean paid;

    private Integer userId;
    private Integer aptId;
    private Integer catId;

}
