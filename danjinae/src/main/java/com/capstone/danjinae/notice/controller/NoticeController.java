package com.capstone.danjinae.notice.controller;
import java.net.URLEncoder;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import com.capstone.danjinae.fcm.service.FcmPush;
import com.capstone.danjinae.notice.DTO.NoticeListResponse;
import com.capstone.danjinae.notice.DTO.NoticeRequest;
import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.notice.service.NoticeService;
import com.capstone.danjinae.user.entity.User;
import com.capstone.danjinae.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    @Autowired
    private FcmPush fcmPush;

    // 공지사항 작성
    @PostMapping("/add")
    public Boolean postNotice(Principal user, @RequestBody NoticeRequest notice) {
        Notice toadd;
        try {
            User aptUser = userService.UserInfoWithPhone(user.getName());
            notice.setAptId(aptUser.getAptId());

            this.setDefaultNoriceRequest(notice);
            toadd = Notice.builder().aptId(notice.getAptId()).content(notice.getContent())
                    .startDate(new Timestamp(notice.getStartDate().getTime()))
                    .endDate(new Timestamp(notice.getEndDate().getTime())).catId(notice.getCatId()).build();

            noticeService.write(toadd);

            List<User> userList= new ArrayList<User>();
            userList= userService.findAllByAptId(notice.getAptId());

            String encodedContent = URLEncoder.encode(notice.getContent(), "UTF-8");
            List<String> token= new ArrayList<String>();

            for(int i=0; i<userList.size();i++){
                token.add(userService.getTokenByUserId(userList.get(i).getId()));
            }

            fcmPush.push(token,encodedContent);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private void setDefaultNoriceRequest(NoticeRequest input) {
        if (input.getStartDate() == null) {
            input.setStartDate(new Date());
        }
        if (input.getEndDate() == null) {
            input.setEndDate(new Date(new Date().getTime() + 1000l * 60 * 60 * 24 * 7));
        }
    }

    @GetMapping("/get")
    public Page<NoticeListResponse> GetNoticeList(
            Principal user,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "userId", required = false) Integer userId) {
        Page<NoticeListResponse> dtoList;

        try {
            User aptUser = userService.UserInfoWithPhone(user.getName());
            userId = aptUser.getId();

            Page<Notice> list = noticeService.getNotices(userId, pageable);
            dtoList = list.map(new Function<Notice, NoticeListResponse>() {

                @Override
                public NoticeListResponse apply(Notice entity) {
                    NoticeListResponse dto = new NoticeListResponse();
                    dto.setCatId(entity.getCatId());
                    dto.setContent(entity.getContent());
                    dto.setEndDate(entity.getEndDate());
                    dto.setStartDate(entity.getStartDate());
                    dto.setRead(noticeService.checkNoticeRead(aptUser.getId(), entity.getId()));
                    dto.setId(entity.getId());
                    return dto;
                }
            });

            noticeService.SetNociesRead(userId, dtoList);
        } catch (Exception e) {
            return null;
        }

        return dtoList;
    }

}