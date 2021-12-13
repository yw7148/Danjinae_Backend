package com.capstone.danjinae.Configuration;

import java.util.Date;

import com.capstone.danjinae.MgFee.entity.MgFee;
import com.capstone.danjinae.MgFee.repository.MgFeeRepository;
import com.capstone.danjinae.MgFee.service.MgFeeService;
import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.notice.repository.NoticeRepository;
import com.capstone.danjinae.notice.service.NoticeService;
import com.capstone.danjinae.vehicle.entity.Vehicle;
import com.capstone.danjinae.vehicle.repository.VehicleRepository;
import com.capstone.danjinae.vehicle.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private final Long DELETE_EXP_DATE = 1000L * 60 * 60 * 24 * 7;

    @Autowired
    MgFeeRepository mgFeeRepository;

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Scheduled(cron = "0 0 3 * * *")
    public void CheckEveryDay()
    {
        try{
            var mgfeeList = mgFeeRepository.findAll();
            for (MgFee mgFee : mgfeeList) {
                if(mgFee.getDate().compareTo(new Date()) == 0)
                    System.out.println("MgFee Day");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        try{
            var noticeList = noticeRepository.findAll();
            for (Notice notice : noticeList) {
                if(notice.getEndDate() != null)
                {
                    if(notice.getEndDate().before(new Date(System.currentTimeMillis() - DELETE_EXP_DATE)))
                        noticeRepository.delete(notice);
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        try{
            var vehicleList = vehicleRepository.findAll();
            for (Vehicle vehicle : vehicleList) {
                if(vehicle.getEndDate() != null)
                {
                    if(vehicle.getEndDate().before(new Date(System.currentTimeMillis() - DELETE_EXP_DATE)))
                        vehicleRepository.delete(vehicle);
                }
            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
