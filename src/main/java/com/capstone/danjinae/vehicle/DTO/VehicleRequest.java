package com.capstone.danjinae.vehicle.DTO;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class VehicleRequest {

    private Integer userId;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String number; //차량 번호
}
