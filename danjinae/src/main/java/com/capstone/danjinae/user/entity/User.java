package com.capstone.danjinae.user.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;
    @Column(name="password")
    private String password;

    @Column(name="apt_id")
    private int aptId;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="birth")
    private Timestamp birth;
    @Column(name="phone")
    private String phone;
    @Column(name="manager")
    private boolean manager;

    public void resident() {
        manager=false;
    }
}
