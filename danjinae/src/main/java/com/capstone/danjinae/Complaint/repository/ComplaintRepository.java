package com.capstone.danjinae.Complaint.repository;

import com.capstone.danjinae.Complaint.entity.Complaint;

import com.capstone.danjinae.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    Page<Complaint> findByAptId(Integer aptId, Pageable pageable);

}
