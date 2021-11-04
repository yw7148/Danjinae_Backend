package com.example.vehicle.controller;

import com.example.vehicle.entity.Vehicle;
import com.example.vehicle.service.VehicleService;
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
    @PutMapping("/car/number")
    public Vehicle carNumber(@RequestBody Vehicle vehicle){
        return vehicleService.writeNumber(vehicle);
    }

    //게스트 차량 등록
    @PutMapping("/car/input")
    public Vehicle carInput(@RequestBody Vehicle vehicle){
        return vehicleService.inputGuest(vehicle);
    }

    //선택된 차량 리스트
    @GetMapping("/car/get/{number}")
    public Page<Vehicle> getCar(Model model, @PageableDefault(page=0,size=10,sort="id",direction= Sort.Direction.DESC) Pageable pageable,@PathVariable String number){

        Page<Vehicle> list= null;

        list= vehicleService.searchnumber(number,pageable);

        int nowPage=list.getPageable().getPageNumber()+1;
        int startPage=Math.max(nowPage-4,1);
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("newPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return list;
    }

    //선택된 차량 세부정보
    @GetMapping("/car/choice/{id}")
    public Vehicle choiceCar(Model model, @PathVariable Integer id){
        model.addAttribute("vehicle",vehicleService.getVehicle(id));
        return vehicleService.getVehicle(id);
    }
}
