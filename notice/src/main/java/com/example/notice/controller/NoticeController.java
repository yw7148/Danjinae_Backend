package com.example.notice.controller;

import com.example.notice.entity.Notice;
import com.example.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //공지사항 작성
    @PutMapping("/addnotice")
    public Notice Addnotice(@RequestBody Notice notice){
        return noticeService.write(notice);
    }
}
