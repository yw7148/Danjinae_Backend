package com.capstone.danjinae.user.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IamportAccessToken {
    private Integer code;
    private String message;
    private Token response;

    @Getter
    @Setter
    public class Token {
        private String access_token;
        private Integer expired_at;
        private Integer now;
    }
}
