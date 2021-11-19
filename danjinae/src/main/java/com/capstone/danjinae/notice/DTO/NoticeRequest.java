package com.capstone.danjinae.notice.DTO;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class NoticeRequest {

    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer catId;
}



