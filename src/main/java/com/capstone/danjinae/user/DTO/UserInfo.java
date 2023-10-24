package com.capstone.danjinae.user.DTO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private int id;

    private String aptAddress;

    private String aptname;

    private String name;

    private String address;

    private Date birth;

    private String phone;

    private boolean manager;
}
