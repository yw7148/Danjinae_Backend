package com.capstone.danjinae.notice.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeListResponse {
    private Integer id;
    private String content;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    private Integer catId;

    private Boolean read;
}
