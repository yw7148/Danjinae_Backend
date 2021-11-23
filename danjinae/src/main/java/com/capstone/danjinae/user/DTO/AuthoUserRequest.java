package com.capstone.danjinae.user.DTO;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
//본인인증 과정에서 저장된  user 정보
public class AuthoUserRequest {

    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;

}
