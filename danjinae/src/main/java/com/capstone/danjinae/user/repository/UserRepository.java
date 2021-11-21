package com.capstone.danjinae.user.repository;

import com.capstone.danjinae.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByAddressAndAptId(String address, Integer aptId);
}
