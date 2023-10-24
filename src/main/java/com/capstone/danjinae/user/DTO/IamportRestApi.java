package com.capstone.danjinae.user.DTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class IamportRestApi {
    @Value("${spring.iamport.restapi}")
    private String imp_key;

    @Value("${spring.iamport.restapisecret}")
    private String imp_secret;

}
