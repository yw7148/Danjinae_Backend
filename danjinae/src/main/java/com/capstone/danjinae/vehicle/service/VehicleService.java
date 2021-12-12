package com.capstone.danjinae.vehicle.service;

import com.capstone.danjinae.post.entity.Post;
import com.capstone.danjinae.vehicle.controller.VehicleController;
import com.capstone.danjinae.vehicle.entity.Vehicle;
import com.capstone.danjinae.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Vehicle writeResidnet(Vehicle vehicle){
        vehicle.resident();
        return vehicleRepository.save(vehicle);
    }

    //게스트 차량 등록
    @Transactional
    public Vehicle writeGuest(Vehicle vehicle){
        vehicle.guest();
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public Page<Vehicle> search(Integer aptId, String number,Pageable pageable){

        return vehicleRepository.findByAcceptAndAptIdAndNumberContaining(true, aptId, number, pageable);

    }

    @Transactional
    public Vehicle get(Integer id){

        return vehicleRepository.findById(id).get();
    }

    public Page<Vehicle> NotAcceptedVahicles(Integer aptId, Pageable pageable){

        return vehicleRepository.findByAcceptAndAptId(false, aptId, pageable);
    }

    public Page<Vehicle> totalVehicleList(Integer aptId, Pageable pageable){

        return vehicleRepository.findByAcceptAndAptId(true, aptId, pageable);
    }

    public Vehicle AcceptVehicle(Integer vehicleId)
    {
        var vehicle = vehicleRepository.getById(vehicleId);
        vehicle.AcceptGuest();
        vehicleRepository.save(vehicle);

        return vehicle;
    }

    @Transactional
    public void deleteVehicle(Integer id)
    {
        vehicleRepository.deleteById(id);
    }

}
