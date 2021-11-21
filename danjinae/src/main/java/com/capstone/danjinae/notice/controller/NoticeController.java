package com.capstone.danjinae.notice.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.function.Function;

import com.capstone.danjinae.notice.DTO.NoticeListResponse;
import com.capstone.danjinae.notice.DTO.NoticeRequest;
import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.notice.service.NoticeService;
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

    // 공지사항 작성
    @PostMapping("/add")
    public Boolean postNotice(@RequestBody NoticeRequest notice) {
        Notice toadd;
        try {
            this.setDefaultNoriceRequest(notice);
            toadd = Notice.builder().aptId(notice.getAptId()).content(notice.getContent())
                    .startDate(new Timestamp(notice.getStartDate().getTime()))
                    .endDate(new Timestamp(notice.getEndDate().getTime())).catId(notice.getCatId()).build();

            noticeService.write(toadd);
        } catch (Exception e) {
            return false;
        }

        return true;
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
            @PageableDefault(page = 0, size = 10, sort = "postId", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "userId") Integer userId) {
        Page<NoticeListResponse> dtoList;
        try {
            Page<Notice> list = noticeService.getNotices(userId, pageable);
            dtoList = list.map(new Function<Notice, NoticeListResponse>() {

                @Override
                public NoticeListResponse apply(Notice entity) {
                    NoticeListResponse dto = new NoticeListResponse();
                    dto.setCatId(entity.getCatId());
                    dto.setContent(entity.getContent());
                    dto.setEndDate(entity.getEndDate());
                    dto.setStartDate(entity.getStartDate());
                    dto.setRead(noticeService.checkNoticeRead(userId, entity.getId()));
                    dto.setId(entity.getId());
                    return dto;
                }
            });
        } catch (Exception e) {
            return null;
        }

        return dtoList;
    }

}
