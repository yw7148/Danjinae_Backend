package com.capstone.danjinae.notice.controller;

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

    @GetMapping("/get")
    public Page<NoticeListResponse> GetNoticeList(@PageableDefault(page=0,size=10,sort="postId",direction= Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "userId") Integer userId)
    {
        Page<NoticeListResponse> dtoList;
        try
        {
            Page<Notice> list = noticeService.getNotices(userId, pageable);
            dtoList = list.map(new Function< Notice, NoticeListResponse> (){

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
        }
        catch(Exception e)
        {
            return null;
        }

        return dtoList;
    }

}
