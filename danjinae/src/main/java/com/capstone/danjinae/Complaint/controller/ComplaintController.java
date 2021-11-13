package com.capstone.danjinae.Complaint.controller;

import com.capstone.danjinae.Complaint.DTO.NewComplaintRequest;
import com.capstone.danjinae.Complaint.entity.Complaint;
import com.capstone.danjinae.Complaint.service.ComplaintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Boolean addNewComplaint(@RequestBody NewComplaintRequest request) {
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

    @GetMapping(value = "/get/{aptid}")
    public Page<Complaint> getManagerComplaintList(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Integer aptid) {

        Page<Complaint> list;
        try {
            list = complaintService.getManagerComplaint(aptid, pageable);
        } catch (Exception e) {
            return null;
        }

        return list;
    }
}
