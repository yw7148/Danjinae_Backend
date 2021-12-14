package com.capstone.danjinae.user.DTO;

import java.io.File;

import lombok.Data;

@Data
public class JoinRequest {
    Integer aptId;
    String phone;
    String password;
    String passwordcheck;
    String name;
    String email;
    File photo;
}
