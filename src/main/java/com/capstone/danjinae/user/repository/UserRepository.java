package com.capstone.danjinae.user.repository;

import com.capstone.danjinae.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByAddressAndAptId(String address, Integer aptId);

    public User findByPhone(String phone);

    public List<User> findAllByAptId(Integer aptid);
}
