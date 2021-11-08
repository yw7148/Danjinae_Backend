package com.capstone.danjinae.vehicle.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="user_id")
    private int userId;
    private String phone;
    private boolean guest;

    @Column(name="start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp toDate;
    private String number; //차량 번호

    //set guest
    public void guest() {
        guest=true;
    }

    public void resident() {
        guest=false;
    }
}
