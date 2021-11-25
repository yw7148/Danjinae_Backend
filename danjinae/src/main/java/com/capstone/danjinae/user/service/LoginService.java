package com.capstone.danjinae.user.service;

import com.capstone.danjinae.user.entity.Salt;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.repository.SaltRepository;
import com.capstone.danjinae.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SaltRepository saltRepository;

    public void SignUpUser(User newUser) {
        String password = newUser.getPassword();
        String salt = genSalt();
        Salt newSalt = Salt.builder().salt(salt).build();
        newUser.passwordSalt(newSalt, encodePassword(salt, password));

        userRepository.save(newUser);
    }

    public User loginUser(String phone, String password) throws Exception {
        User user = userRepository.findByPhone(phone);
        if (user == null || !phone.equals(user.getPhone()))
            throw new Exception("유저가 조회되지 않음");
        String salt = user.getSalt().getSalt();
        password = encodePassword(salt, password);
        if (!user.getPassword().equals(password))
            throw new Exception("비밀번호가 틀립니다.");
        return user;
    }

    public void deleteUser(Integer userId) throws Exception {
        User user = userRepository.getById(userId);
        if (user == null)
            throw new Exception("유저가 조회되지 않음");

        Salt delSalt = user.getSalt();

        userRepository.delete(user);
        saltRepository.delete(delSalt);
    }

    public String getRole(Integer userId) throws Exception {
        User user = userRepository.getById(userId);
        if (user == null)
            throw new Exception("오류가 발생했습니다.");

        return user.getRole().toString();

    }

    private String encodePassword(String salt, String password) {
        return BCrypt.hashpw(password, salt);
    }

    private String genSalt() {
        return BCrypt.gensalt();
    }
}
