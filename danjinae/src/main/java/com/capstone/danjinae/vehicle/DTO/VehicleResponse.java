package com.capstone.danjinae.vehicle.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class VehicleResponse {
    private Integer vehicleId;
    private Integer userId;
    private String phone;
    private boolean guest;
    private Timestamp startDate;
    private Timestamp endDate;
    private String number; //차량 번호
}
