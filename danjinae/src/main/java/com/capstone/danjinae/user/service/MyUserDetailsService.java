package com.capstone.danjinae.user.service;

import com.capstone.danjinae.user.entity.AuthUser;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        User user = userRepository.findByPhone(phone);
        if (user == null || !phone.equals(user.getPhone())) {
            try {
                throw new UsernameNotFoundException(phone + " : 사용자 존재하지 않음");
            } catch (UsernameNotFoundException e) {
            }
        }

        return new AuthUser(user);
    }
}