package com.capstone.danjinae.Complaint.repository;
import com.capstone.danjinae.Complaint.entity.ComplaintProcess;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CplProcessRepository extends JpaRepository<ComplaintProcess, Integer> {
    Page<ComplaintProcess> findByCplId(Integer cplId, Pageable pageable);
}