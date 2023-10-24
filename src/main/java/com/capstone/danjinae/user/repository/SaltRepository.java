package com.capstone.danjinae.user.repository;

import com.capstone.danjinae.user.entity.Salt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaltRepository extends JpaRepository<Salt, Integer> {

}
