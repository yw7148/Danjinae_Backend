package com.capstone.danjinae.MgFee.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerMgFeeResponse {
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer fee;
    private Boolean paid;

    private String address;
    private Integer aptId;
    private Integer catId;
}
