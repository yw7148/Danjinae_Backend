
package com.example.notice.service;

import com.example.notice.entity.Notice;
import com.example.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Notice write(Notice notice){

        noticeRepository.save(notice);
        return notice;
    }


}
