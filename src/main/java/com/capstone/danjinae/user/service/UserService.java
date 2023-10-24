package com.capstone.danjinae.user.service;

import com.capstone.danjinae.Complaint.entity.Complaint;
import com.capstone.danjinae.Complaint.repository.ComplaintRepository;
import com.capstone.danjinae.Complaint.service.ComplaintService;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.entity.UserFCMToken;
import com.capstone.danjinae.user.repository.UserFCMTokenRepository;
import com.capstone.danjinae.user.repository.UserRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFCMTokenRepository userFCMTokenRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ComplaintService complaintService;

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
        UserFCMToken fcmToken = userFCMTokenRepository.findByToken(token);
        if(fcmToken != null)
            userFCMTokenRepository.delete(fcmToken);
            
        UserFCMToken newToken = UserFCMToken.builder().userId(userId).token(token).build();
        userFCMTokenRepository.save(newToken);

        return true;
    }

    public List<String> getToken (Integer cplid){
        Complaint cpl = complaintService.getComplaint(cplid);
        List<UserFCMToken> userToken= userFCMTokenRepository.findAllByUserId(cpl.getUserId());
        
        List<String> tokens = new ArrayList<String>(userToken.size());
        for (UserFCMToken userFCMToken : userToken) {
            tokens.add(userFCMToken.getToken());
        }
        return tokens;
    }

    public List<User> findAllByAptId(Integer aptid){
        return userRepository.findAllByAptId(aptid);
    }

    public List<String> getTokenByUserId(Integer userId){
        List<UserFCMToken> userToken= userFCMTokenRepository.findAllByUserId(userId);
        List<String> tokens = new ArrayList<String>(userToken.size());
        for (UserFCMToken userFCMToken : userToken) {
            tokens.add(userFCMToken.getToken());
        }

        return tokens;
    }
}
