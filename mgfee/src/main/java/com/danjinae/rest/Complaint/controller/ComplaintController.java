package com.danjinae.rest.Complaint.controller;

import com.danjinae.rest.Complaint.DTO.NewComplaintRequest;
import com.danjinae.rest.Complaint.entity.Complaint;
import com.danjinae.rest.Complaint.service.ComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping(value = "/add")
    public Boolean postMethodName(@RequestBody NewComplaintRequest request) {
        Complaint toadd;
        try {
            toadd = Complaint.builder().content(request.getContent()).userId(request.getUserId())
                    .aptId(request.getAptId()).build();

            complaintService.write(toadd);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
