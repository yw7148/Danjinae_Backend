package com.capstone.danjinae.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "Salts")
@Getter
public class Salt {
    @Id
    @GeneratedValue
    private int saltID;

    private String salt;

    @Builder
    public Salt(String salt) {
        this.salt = salt;
    }
}
