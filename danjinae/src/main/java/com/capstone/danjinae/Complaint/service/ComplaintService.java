package com.capstone.danjinae.Complaint.service;

import com.capstone.danjinae.Complaint.entity.Complaint;
import com.capstone.danjinae.Complaint.repository.ComplaintRepository;
import com.capstone.danjinae.MgFee.entity.MgFee;
import com.capstone.danjinae.MgFee.repository.MgFeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public Complaint write(Complaint complaint) {

        complaintRepository.save(complaint);
        return complaint;
    }

    public Complaint getComplaint(Integer id) {

        return complaintRepository.findById(id).get();
    }

    public void deleteComplaint(Integer id) {

        complaintRepository.deleteById(id);
    }

}
