package com.danjinae.rest.Complaint.repository;

import com.danjinae.rest.Complaint.entity.Complaint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
}
