package com.capstone.danjinae.vehicle.controller;

import com.capstone.danjinae.vehicle.entity.Vehicle;
import com.capstone.danjinae.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    //차량 등록
    @PostMapping("/resident")
    public Vehicle inputResident(@RequestBody Vehicle vehicle){
        return vehicleService.writeResidnet(vehicle);
    }

    //게스트 차량 등록
    @PostMapping("/guest")
    public Vehicle inputGuest(@RequestBody Vehicle vehicle){
        return vehicleService.writeGuest(vehicle);
    }

    //선택된 차량 리스트
    @GetMapping("/select/list")
    public Page<Vehicle> selectList(@PageableDefault(page=0,size=10,sort="id",direction= Sort.Direction.DESC) Pageable pageable,@RequestParam("number") String number){

        Page<Vehicle> list= null;

        list= vehicleService.search(number,pageable);

        int nowPage=list.getPageable().getPageNumber()+1;
        int startPage=Math.max(nowPage-4,1);
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        return list;
    }

    //선택된 차량 세부정보
    @GetMapping("/select/info")
    public Vehicle selectInfo(@RequestParam("id") Integer id){

        return vehicleService.get(id);
    }
}
