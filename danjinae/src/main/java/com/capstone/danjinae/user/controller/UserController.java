package com.capstone.danjinae.user.controller;

import com.capstone.danjinae.notice.DTO.NoticeRequest;
import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.user.DTO.UserRequest;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.service.UserService;
import com.capstone.danjinae.vehicle.entity.Vehicle;
import com.capstone.danjinae.vehicle.repository.VehicleRepository;
import com.capstone.danjinae.vehicle.service.VehicleService;

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
}
