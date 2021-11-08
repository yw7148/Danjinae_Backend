package com.danjinae.rest.Complaint.service;

import com.danjinae.rest.Complaint.entity.Complaint;
import com.danjinae.rest.Complaint.repository.ComplaintRepository;
import com.danjinae.rest.MgFee.entity.MgFee;
import com.danjinae.rest.MgFee.repository.MgFeeRepository;

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

    public Complaint getMgFee(Integer id) {

        return complaintRepository.findById(id).get();
    }

    public void deleteComplaint(Integer id) {

        complaintRepository.deleteById(id);
    }

}
