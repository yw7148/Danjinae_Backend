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
    @Column(name = "user_id")
    private int id;
    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Salt_saltID")
    private Salt salt;

    @Column(name = "apt_id")
    private int aptId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "birth")
    private Timestamp birth;
    @Column(name = "phone", unique = true)
    private String phone;
    @Column(name = "manager")
    private boolean manager;

    @Column(name = "role")
    private UserRole role = UserRole.ROLE_NOT_PERMITTED;

    @Builder
    public User(int aptId, String name, String address, Timestamp birth, String phone) {
        this.aptId = aptId;
        this.name = name;
        this.address = address;
        this.birth = birth;
        this.phone = phone;
    }

    public User passwordSalt(Salt salt, String newPasswordWithSalt) {
        this.salt = salt;
        this.password = newPasswordWithSalt;
        return this;
    }

    public void Resident() {
        manager = false;
        this.role = UserRole.ROLE_RESIDENT;
    }

    public void Manager() {
        this.role = UserRole.ROLE_MANAGER;
    }

    public void MangerRequest() {
        this.role = UserRole.ROLE_NOT_PERMITTED;
    }
}
