package com.capstone.danjinae.user.DTO;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@Getter
@Setter
public class AuthoUserRequest {
    private Integer aptId;
    private String impId;
    private String phone;
    private String password;

}
