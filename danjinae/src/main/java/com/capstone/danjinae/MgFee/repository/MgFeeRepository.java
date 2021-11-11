package com.capstone.danjinae.MgFee.repository;

import com.capstone.danjinae.MgFee.entity.MgFee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MgFeeRepository extends JpaRepository<MgFee, Integer> {

    // findBy(컬럼이름)Containing
    Page<MgFee> findByUserIdAndAptId(Integer userId, Integer aptId, Pageable pageable);
}
