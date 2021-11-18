package com.capstone.danjinae.notice.controller;

import com.capstone.danjinae.MgFee.DTO.NewMgFeeRequest;
import com.capstone.danjinae.MgFee.entity.MgFee;
import com.capstone.danjinae.notice.DTO.NoticeRequest;
import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //공지사항 작성
    @PostMapping("/add")
    public Boolean postNotice(@RequestBody NoticeRequest notice){
        Notice toadd;
        try {
            toadd = Notice.builder().content(notice.getContent()).startDate(notice.getStartDate()).endDate(notice.getEndDate())
                    .catId(notice.getCatId()).build();

            noticeService.write(toadd);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
