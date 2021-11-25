package com.capstone.danjinae.user.JWT.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "RefreshToken")
@Getter
public class RefreshToken {
    @Id
    @GeneratedValue
    private int tokenID;

    @Column(unique = true)
    private String refreshToken;

    private String username;

    @Builder
    public RefreshToken(String refreshToken, String username) {
        this.refreshToken = refreshToken;
        this.username = username;
    }
}
