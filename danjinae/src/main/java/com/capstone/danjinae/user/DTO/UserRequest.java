package com.capstone.danjinae.user.DTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class UserRequest {

    private int aptId;
    private String name;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;

}
