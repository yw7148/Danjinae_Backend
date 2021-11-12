package com.capstone.danjinae.user.controller;

import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //입주민 등록
    @PostMapping("/user")
    public User inputUser(@RequestBody User user){

        return userService.writeUser(user);
    }
}
