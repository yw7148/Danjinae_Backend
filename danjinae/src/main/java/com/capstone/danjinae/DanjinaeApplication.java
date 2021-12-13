package com.capstone.danjinae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DanjinaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanjinaeApplication.class, args);
    }

}
