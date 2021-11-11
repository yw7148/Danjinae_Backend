package com.capstone.danjinae.MgFee.service;

import com.capstone.danjinae.MgFee.entity.MgFee;
import com.capstone.danjinae.MgFee.repository.MgFeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MgFeeService {

    @Autowired
    private MgFeeRepository mgFeeRepository;

    public MgFee write(MgFee mgFee) {

        mgFeeRepository.save(mgFee);
        return mgFee;
    }

    public Page<MgFee> getUserMgFeeList(Integer userId, Integer aptId, Pageable pageable) {

        return mgFeeRepository.findByUserIdAndAptId(userId, aptId, pageable);
    }

    public MgFee getMgFee(Integer id) {

        return mgFeeRepository.findById(id).get();
    }

    public void deleteMgFee(Integer id) {

        mgFeeRepository.deleteById(id);
    }

}
