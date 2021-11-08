package com.danjinae.rest.MgFee.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMgFeeRequest {
    private Integer fee;

    private Integer userId;
    private Integer aptId;
    private Integer catId;
}
