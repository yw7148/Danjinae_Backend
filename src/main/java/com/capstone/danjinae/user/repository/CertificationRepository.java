package com.capstone.danjinae.user.repository;

import com.capstone.danjinae.user.entity.CertificateEmployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<CertificateEmployment, Integer> {
}
