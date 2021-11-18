package com.capstone.danjinae.user.service;

import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User writeUser(User user){
        user.resident();
        return userRepository.save(user);
    }
}
