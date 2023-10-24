package com.capstone.danjinae.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CollectionId;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserFCMToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    Integer userId;

    @Column(name = "token")
    String token;

    @Builder
    UserFCMToken (Integer userId, String token)
    {
        this.userId =userId;
        this.token = token;
    }
}
