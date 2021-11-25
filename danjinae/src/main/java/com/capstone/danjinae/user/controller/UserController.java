package com.capstone.danjinae.user.controller;

import com.capstone.danjinae.notice.DTO.NoticeRequest;
import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.user.DTO.AddUserRequest;
import com.capstone.danjinae.user.DTO.AuthoUserRequest;
import com.capstone.danjinae.user.DTO.LoginUserRequest;
import com.capstone.danjinae.user.DTO.UserRequest;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.service.UserService;
import com.capstone.danjinae.vehicle.entity.Vehicle;
import com.capstone.danjinae.vehicle.repository.VehicleRepository;
import com.capstone.danjinae.vehicle.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    // 입주민 등록
    @PostMapping("/add")
    public Boolean inputUser(@RequestBody UserRequest user) {
        try {
            User toadd;
            User mgr = userService.getUser(user.getMgrId());
            toadd = User.builder().aptId(mgr.getAptId()).name(user.getName()).address(user.getAddress())
                    .birth(new Timestamp(user.getBirth().getTime())).phone(user.getPhone()).build();
            toadd.Resident();

            User addedUser = userService.writeUser(toadd);

            if (user.getCarnumber() != null && user.getCarphone() != null) {
                Vehicle vtoadd = Vehicle.builder().number(user.getCarnumber()).phone(user.getCarphone())
                        .userId(addedUser.getId()).build();
                vehicleService.writeResidnet(vtoadd);
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // 입주민 인증
    @PostMapping("/authorization")
    public Boolean authorization(@RequestBody AuthoUserRequest request, HttpServletResponse response) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/login")
    public Boolean Login(@RequestBody LoginUserRequest request, HttpServletResponse response) {
        try {
            response.setHeader("ACCESS_TOKEN", "123124");
            response.setHeader("REFRESH_TOKEN", "112233");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
