package com.capstone.danjinae.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public User(int aptId, String name, String address, Timestamp birth,String phone ) {
        this.aptId = aptId;
        this.name = name;
        this.address = address;
        this.birth = birth;
        this.phone=phone;
    }
}
