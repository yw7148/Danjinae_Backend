package com.capstone.danjinae.notice.DTO;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class NoticeRequest {

    private String content;
    private Timestamp startDate;
    private Timestamp endDate;
    private Integer catId;
}



