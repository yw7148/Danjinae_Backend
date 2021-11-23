package com.capstone.danjinae.user.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class AddUserRequest {

    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;
    private int aptId;
}
