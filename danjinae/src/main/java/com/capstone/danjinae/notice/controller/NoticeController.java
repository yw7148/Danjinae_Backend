package com.capstone.danjinae.notice.controller;

import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //공지사항 작성
    @PostMapping("/notice")
    public Notice notice(@RequestBody Notice notice){
        return noticeService.write(notice);
    }
}
