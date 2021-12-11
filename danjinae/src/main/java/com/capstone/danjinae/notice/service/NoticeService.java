
package com.capstone.danjinae.notice.service;

import com.capstone.danjinae.notice.entity.Notice;
import com.capstone.danjinae.notice.entity.NoticeReadHistory;
import com.capstone.danjinae.notice.repository.NoticeReadRepository;
import com.capstone.danjinae.notice.repository.NoticeRepository;
import com.capstone.danjinae.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeReadRepository noticeReadRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Notice write(Notice notice){

        noticeRepository.save(notice);
        return notice;
    }

    public Page<Notice> getNotices(Integer userId, Pageable pageable)
    {
        Integer aptId = userRepository.getById(userId).getAptId();
        return noticeRepository.findByAptId(aptId, pageable);
    }

    public Boolean SetNociesRead(Integer userId, Page<Notice> notices)
    {
        for (Notice notice : notices.getContent()) {
            NoticeReadHistory read = NoticeReadHistory.builder().userId(userId).noticeId(notice.getId()).build();
            if(noticeReadRepository.findAllByUserIdAndNoticeId(userId, notice.getId()) == null);
                noticeReadRepository.save(read);
        }

        return true;
    }
    
    public Boolean checkNoticeRead(Integer userId, Integer noticeId)
    {
        var result = noticeReadRepository.findAllByUserIdAndNoticeId(userId, noticeId);
        return (result != null);
    }


}
