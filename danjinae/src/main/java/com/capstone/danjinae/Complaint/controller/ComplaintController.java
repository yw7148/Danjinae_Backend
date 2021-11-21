package com.capstone.danjinae.Complaint.controller;

import java.util.function.Function;

import com.capstone.danjinae.Complaint.DTO.ComplaintListResponse;
import com.capstone.danjinae.Complaint.DTO.ComplaintResponse;
import com.capstone.danjinae.Complaint.DTO.CplProcessResponse;
import com.capstone.danjinae.Complaint.DTO.NewComplaintRequest;
import com.capstone.danjinae.Complaint.DTO.NewCplProcessRequest;
import com.capstone.danjinae.Complaint.DTO.NewComplaintResponse;
import com.capstone.danjinae.Complaint.entity.Complaint;
import com.capstone.danjinae.Complaint.entity.ComplaintProcess;
import com.capstone.danjinae.Complaint.service.ComplaintService;

import com.capstone.danjinae.post.DTO.postDTO.PostResponse;
import com.capstone.danjinae.post.entity.Post;
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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/addprocess")
    public Boolean addNewCplProcess(@RequestBody NewCplProcessRequest request) {
        ComplaintProcess toadd;
        try {
            toadd = ComplaintProcess.builder().content(request.getContent()).cplId(request.getCplId())
                    .mgrId(request.getMgrId()).state(request.getState()).build();

            complaintService.writeCplProcess(toadd);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @GetMapping(value = "/get/{aptid}")
    public Page<ComplaintListResponse> getManagerComplaintList(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Integer aptid) {

        Page<ComplaintListResponse> list;
        try {
            Page<Complaint> ettlist = complaintService.getManagerComplaint(aptid, pageable);
            list = ettlist.map(new Function<Complaint, ComplaintListResponse>() {
                @Override
                public ComplaintListResponse apply(Complaint ett) {
                    ComplaintListResponse dto = new ComplaintListResponse();
                    dto.setContent(ett.getContent());
                    dto.setCplId(ett.getId());
                    return dto;
                }
            });
        } catch (Exception e) {
            return null;
        }

        return list;
    }

    @GetMapping(value = "/select/{cplid}")
    public ComplaintResponse getManagerComplaint(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Integer cplid) {

        ComplaintResponse result = new ComplaintResponse();
        try {
            Complaint target = complaintService.getComplaint(cplid);
            Page<ComplaintProcess> ettlist = complaintService.getComplaintProcess(cplid, pageable);
            result.setProcesses(ettlist.map(new Function<ComplaintProcess, CplProcessResponse>() {
                @Override
                public CplProcessResponse apply(ComplaintProcess entity) {
                    CplProcessResponse dto = new CplProcessResponse();
                    dto.setContent(entity.getContent());
                    dto.setMgrId(entity.getMgrId());
                    dto.setState(entity.getState());
                    return dto;
                }
            }));
            result.setCplId(cplid);
            result.setContent(target.getContent());
            result.setUserId(target.getUserId());
        } catch (Exception e) {
            return result;
        }

        return result;
    }

}
