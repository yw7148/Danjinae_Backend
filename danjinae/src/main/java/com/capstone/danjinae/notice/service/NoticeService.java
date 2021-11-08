
package com.capstone.danjinae.notice.service;

import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Transactional
    public Notice write(Notice notice){

        noticeRepository.save(notice);
        return notice;
    }


}
