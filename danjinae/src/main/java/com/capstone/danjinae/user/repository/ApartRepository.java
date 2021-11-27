package com.capstone.danjinae.user.repository;

import com.capstone.danjinae.user.entity.Apartment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartRepository extends JpaRepository<Apartment, Integer> {
    Page<Apartment> findByAddressContaining(String address, Pageable pageable);
}
