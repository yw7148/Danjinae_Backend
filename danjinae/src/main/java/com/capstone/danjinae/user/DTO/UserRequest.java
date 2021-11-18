package com.capstone.danjinae.user.DTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
public class UserRequest {

    private int aptId;
    private String name;
    private String address;
    private Timestamp birth;
    private String phone;

}
