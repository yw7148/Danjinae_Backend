package com.capstone.danjinae.user.controller;
import com.capstone.danjinae.notice.DTO.NoticeRequest;
import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.user.DTO.UserRequest;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //입주민 등록
    @PostMapping("/add")
    public Boolean inputUser(@RequestBody UserRequest user){
        User toadd;

        try {
            toadd = User.builder().aptId(user.getAptId()).name(user.getName()).address(user.getAddress())
                    .birth(user.getBirth()).phone(user.getPhone()).build();

            userService.writeUser(toadd);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}


