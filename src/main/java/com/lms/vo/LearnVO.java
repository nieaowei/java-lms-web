package com.lms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.JoinColumn;

@Data
public class LearnVO {
    @JsonProperty("dlid")
    private Integer docListId;
    @JsonProperty("name")
    private String docListName;
    @JsonProperty("total_dur")
    private Integer totalDuration;
    @JsonProperty("curr_dur")
    private Integer currentDuration;
}
