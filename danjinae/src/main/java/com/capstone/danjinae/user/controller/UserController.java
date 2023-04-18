package com.capstone.danjinae.user.controller;

import com.capstone.danjinae.notice.DTO.NoticeRequest;
import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.user.DTO.AddUserRequest;
import com.capstone.danjinae.user.DTO.AptListResponse;
import com.capstone.danjinae.user.DTO.AuthoUserRequest;
import com.capstone.danjinae.user.DTO.FCMToken;
import com.capstone.danjinae.user.DTO.JoinRequest;
import com.capstone.danjinae.user.DTO.LoginUserRequest;
import com.capstone.danjinae.user.DTO.UserInfo;
import com.capstone.danjinae.user.DTO.UserRequest;
import com.capstone.danjinae.user.JWT.service.JwtService;
import com.capstone.danjinae.user.entity.Apartment;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.entity.CertificateEmployment;
import com.capstone.danjinae.user.service.ApartService;
import com.capstone.danjinae.user.service.CertificationService;
import com.capstone.danjinae.user.service.LoginService;
import com.capstone.danjinae.user.service.UserService;
import com.capstone.danjinae.vehicle.entity.Vehicle;
import com.capstone.danjinae.vehicle.repository.VehicleRepository;
import com.capstone.danjinae.vehicle.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import lombok.val;

import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApartService apartService;

    @Autowired
    private CertificationService certificationService;

    // 입주민 등록
    @Secured(value = "ROLE_MANAGER")
    @PostMapping("/add")
    public Boolean inputUser(Principal loginUser, @RequestBody UserRequest user) {
        try {
            User toadd;
            User aptUser = userService.UserInfoWithPhone(loginUser.getName());

            User mgr = userService.getUser(aptUser.getId());

            toadd = User.builder().aptId(mgr.getAptId()).name(user.getName()).address(user.getAddress())
                    .birth(new Timestamp(user.getBirth().getTime())).phone(user.getPhone()).build();
            toadd.Resident();

            User addedUser = userService.writeUser(toadd);

            if (user.getCarnumber() != null && user.getCarphone() != null) {
                Vehicle vtoadd = Vehicle.builder().number(user.getCarnumber()).phone(user.getCarphone())
                        .userId(addedUser.getId()).build();
                vehicleService.writeResidnet(vtoadd);
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @GetMapping("/aptchoice")
    public Page<AptListResponse> chooseApt(
            @PageableDefault(page = 0, size = 10, sort = "aptId", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "name", required = false) String name) {

        Page<AptListResponse> dtolist;
        try {
            if (address == null) {
                address = "";
            }
            if(name == null){
                name = "";
            }
            var list = apartService.searchAddress(address, name, pageable);
            dtolist = list.map(new Function<Apartment, AptListResponse>() {

                @Override
                public AptListResponse apply(Apartment entity) {
                    AptListResponse dto = new AptListResponse();
                    dto.setAptId(entity.getAptId());
                    dto.setAddress(entity.getAddress());
                    dto.setName(entity.getName());
                    return dto;
                }
            });
        } catch (Exception e) {
            return null;
        }

        return dtolist;
    }

    // 입주민 인증
    @PostMapping("/authorization")
    public Boolean authorization(@RequestBody AuthoUserRequest request, HttpServletResponse response) {
        try {
            User newUser = loginService.CheckResident(request);
            
            if (newUser == null)
                return false;
            else
                return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PutMapping("/signup")
    public Boolean SignUp(@RequestBody LoginUserRequest request) {

        try {
            return loginService.SignUpUser(request.getPhone(), request.getPassword());
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/joinmanager")
    public Boolean JoinManager(@RequestBody JoinRequest user) {
        try {
            User toadd;

            toadd = User.builder().aptId(user.getAptId()).name(user.getName())
                    .phone(user.getPhone()).build();
            
            toadd.MangerRequest();
            User addedUser = userService.writeUser(toadd);

            loginService.SignUpUser(user.getPhone(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @PostMapping("/login")
    public Boolean Login(@RequestBody LoginUserRequest request, HttpServletResponse response) {
        try {
            User user = loginService.loginUser(request.getPhone(), request.getPassword());
            String token = jwtService.generateToken(request.getPhone());
            String refreshJwt = jwtService.generateRefreshToken(user);

            response.setHeader(JwtService.ACCESS_TOKEN_NAME, token);
            response.setHeader(JwtService.REFRESH_TOKEN_NAME, refreshJwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/logout")
    @Secured(value = {"USER_RESIDENT", "USER_MANAGER", "USER_ADMIN"})
    public Boolean Logout(HttpServletRequest req, @RequestBody LoginUserRequest request, HttpServletResponse response) {
        try {
            String refreshtoken = req.getHeader(JwtService.REFRESH_TOKEN_NAME);
            return jwtService.deleteToken(refreshtoken);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/upload")
    public Boolean upload(Principal user, @RequestParam(value = "userId", required = false) Integer userId,MultipartFile file) throws Exception {
        try {
            User aptUser = userService.UserInfoWithPhone(user.getName());
            userId = aptUser.getId();
            CertificateEmployment certification;
            certification= CertificateEmployment.builder().userId(userId).build();
            certificationService.upload(certification, file);


            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @GetMapping("/myinfo")
    public UserInfo userInfo(Principal user)
    {
        try{
            User userinfo = userService.UserInfoWithPhone(user.getName());
            UserInfo returnDto = new UserInfo();
            returnDto.setAddress(userinfo.getAddress());
            returnDto.setBirth(userinfo.getBirth());
            returnDto.setManager(userinfo.isManager());
            returnDto.setName(userinfo.getName());
            returnDto.setPhone(userinfo.getPhone());
            returnDto.setId(userinfo.getId());

            Apartment aptInfo = apartService.getApartment(userinfo.getAptId());
            returnDto.setAptname(aptInfo.getName());
            returnDto.setAddress(aptInfo.getAddress());

            return returnDto;
        }catch(Exception e) 
        {
            return null;
        }
    }


    @Secured(value = "USER_RESIDENT")
    @PostMapping("/newfcmtoken")
    public Boolean NewUserFCMToken(Principal user, @RequestBody FCMToken token)
    {
        try{
            Integer userId = userService.UserInfoWithPhone(user.getName()).getId();
            return userService.UserNewFCMToken(userId, token.getToken());
        }catch(Exception e) 
        {
            return false;
        }
    }

    @GetMapping("/validate")
    @Secured(value = {"USER_RESIDENT", "USER_MANAGER", "USER_ADMIN"})
    public Boolean ValidateUser()
    {
        return true;
    }
}
