package com.capstone.danjinae.user.repository;

import com.capstone.danjinae.user.JWT.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<RefreshToken, Integer> {
    RefreshToken findByRefreshToken(String refreshToken);
}