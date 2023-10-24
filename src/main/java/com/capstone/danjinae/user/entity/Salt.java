package com.capstone.danjinae.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Salts")
@Getter
@NoArgsConstructor
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
