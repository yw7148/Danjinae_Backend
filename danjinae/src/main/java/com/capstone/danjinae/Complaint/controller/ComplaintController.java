package com.capstone.danjinae.Complaint.controller;

import com.capstone.danjinae.Complaint.DTO.NewComplaintRequest;
import com.capstone.danjinae.Complaint.DTO.NewComplaintResponse;
import com.capstone.danjinae.Complaint.entity.Complaint;
import com.capstone.danjinae.Complaint.service.ComplaintService;

import com.capstone.danjinae.post.DTO.postDTO.PostResponse;
import com.capstone.danjinae.post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping(value = "/add")
    public Boolean postMethodName(@RequestBody NewComplaintRequest request){
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

    @GetMapping("/select")
    public NewComplaintResponse selectPost(@RequestParam("id") Integer id){
        Complaint complaint = complaintService.getComplaint(id);
        NewComplaintResponse dto = new NewComplaintResponse();

        dto.setId(complaint.getId());
        dto.setContent(complaint.getContent());
        dto.setUserId(complaint.getUserId());
        dto.setAptId(complaint.getAptId());
        return dto;
    }

}
