package com.capstone.danjinae.vehicle.DTO;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class VehicleRequest {

    private Integer userId;
    private String phone;
    private Timestamp startDate;
    private Timestamp endDate;
    private String number; //차량 번호
}
