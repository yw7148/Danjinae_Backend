package com.capstone.danjinae.user.service;

import java.util.HashMap;

import com.capstone.danjinae.user.DTO.AuthoUserRequest;
import com.capstone.danjinae.user.DTO.IamportAccessToken;
import com.capstone.danjinae.user.DTO.IamportResponse;
import com.capstone.danjinae.user.DTO.IamportRestApi;
import com.capstone.danjinae.user.entity.Salt;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.repository.SaltRepository;
import com.capstone.danjinae.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SaltRepository saltRepository;

    @Autowired
    IamportRestApi iamportRestApi;
    RestTemplate restTemplate = new RestTemplate();

    public User CheckResident(AuthoUserRequest newUserReq) {
        User newUser = userRepository.findByPhone(newUserReq.getPhone());
        if (newUser == null)
            return null;
        else if (newUser.getAptId() != newUserReq.getAptId())
            return null;
        else {
            HttpEntity<IamportRestApi> request = new HttpEntity<IamportRestApi>(iamportRestApi);
            var key = restTemplate.exchange("https://api.iamport.kr/users/getToken", HttpMethod.POST, request,
                    IamportAccessToken.class);

            HttpHeaders header = new HttpHeaders();
            String accessToken = key.getBody().getResponse().getAccess_token();
            header.add("Authorization", accessToken);
            request = new HttpEntity<IamportRestApi>(null, header);
            try {
                restTemplate.exchange("https://api.iamport.kr/certifications/" + newUserReq.getImpId(), HttpMethod.GET,
                        request, IamportResponse.class);

                return newUser;
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                    return null;

                return null;
            }
        }

    }

    public Boolean SignUpUser(String phone, String password) {

        User newUser = userRepository.findByPhone(phone);
        String salt = genSalt();
        Salt newSalt = Salt.builder().salt(salt).build();
        newUser.passwordSalt(newSalt, encodePassword(salt, password));
        userRepository.save(newUser);

        return true;
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
