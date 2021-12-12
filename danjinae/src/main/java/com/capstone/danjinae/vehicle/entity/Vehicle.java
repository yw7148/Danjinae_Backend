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

    @Column(name = "apt_id")
    private Integer aptId;

    @Column(name="end_date")
    private Timestamp endDate;

    @Column(name="number")
    private String number; //차량 번호

    @Column(name = "accept")
    private Boolean accept;

    //set guest
    public void guest() {
        guest=true;
    }

    public void resident() {
        accept = true;
        guest=false;
    }

    @Builder
    public Vehicle(Integer userId, Integer aptId,String phone,Timestamp startDate,Timestamp endDate,String number){
        this.aptId = aptId;
        this.userId=userId;
        this.phone=phone;
        this.startDate=startDate;
        this.endDate=endDate;
        this.number=number;
    }

    public boolean getGuest() {
        return guest;
    }

    public void AcceptGuest()
    {
        this.accept = true;
    }
}


