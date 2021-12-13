package com.capstone.danjinae.vehicle.repository;

import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.vehicle.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    Page<Vehicle> findByAcceptAndAptIdAndNumberContaining(Boolean accept, Integer aptId, String number, Pageable pageable);

    Page<Vehicle> findByAcceptAndAptId(Boolean accept, Integer aptId, Pageable pageable);

    Page<Vehicle> findByPhone(String phone,Pageable pageable);
}
