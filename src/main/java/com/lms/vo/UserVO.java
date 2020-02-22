package com.lms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserVO {
    @JsonProperty("uiid")
    private Long uiid;
}
