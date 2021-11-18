package com.capstone.danjinae.notice.DTO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeListResponse {
    private Integer id;
    private String content;
    private Date startDate;
    private Date endDate;
    private Integer catId;

    private Boolean read;
}
