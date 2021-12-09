package com.capstone.danjinae.user.service;

import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.entity.UserFCMToken;
import com.capstone.danjinae.user.repository.UserFCMTokenRepository;
import com.capstone.danjinae.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFCMTokenRepository userFCMTokenRepository;

    @Transactional
    public User writeUser(User user) {
        return userRepository.save(user);
    }

    public User getUserWithAddress(String address, Integer aptId) {
        return userRepository.findByAddressAndAptId(address, aptId);
    }

    public User getUser(Integer id) {
        return userRepository.getById(id);
    }

    public User UserInfoWithPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public Boolean UserNewFCMToken(Integer userId, String token)
    {
        UserFCMToken newToken = UserFCMToken.builder().userId(userId).token(token).build();
        userFCMTokenRepository.save(newToken);

        return true;
    }
}
