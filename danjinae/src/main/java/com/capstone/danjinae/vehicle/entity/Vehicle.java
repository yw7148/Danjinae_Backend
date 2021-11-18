package com.capstone.danjinae.vehicle.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="user_id")
    private Integer userId;
    @Column(name="phone")
    private String phone;
    @Column(name="guest")
    private boolean guest;

    @Column(name="start_date")
    private Timestamp startDate;

    @Column(name="end_date")
    private Timestamp endDate;
    @Column(name="number")
    private String number; //차량 번호

    //set guest
    public void guest() {
        guest=true;
    }

    public void resident() {
        guest=false;
    }

    @Builder
    public Vehicle(Integer userId,String phone,Timestamp startDate,Timestamp endDate,String number){

        this.userId=userId;
        this.phone=phone;
        this.startDate=startDate;
        this.endDate=endDate;
        this.number=number;
    }

    public boolean getGuest() {
        return guest;
    }
}


