package com.example.vehicle.service;

import com.example.vehicle.controller.VehicleController;
import com.example.vehicle.entity.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    //입주민 차량 등록
    public Vehicle writeNumber(Vehicle vehicle){
        vehicle.setIsguest(false);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle inputGuest(Vehicle vehicle){
        vehicle.setIsguest(true);
        return vehicleRepository.save(vehicle);
    }

    public Page<Vehicle> searchnumber(String number,Pageable pageable){

        return vehicleRepository.findByNumberContaining(number, pageable);

    }

    public Vehicle getVehicle(Integer id){

        return vehicleRepository.findById(id).get();
    }

}
