package com.capstone.danjinae.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apartment")
@Getter
@NoArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apt_id")
    private int aptId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Builder
    public Apartment(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
