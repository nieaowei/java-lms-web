package com.lms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.JoinColumn;
import java.io.Serializable;

@Data
//@AllArgsConstructor
@JsonSerialize
public class LearnVO implements Serializable {
    private Integer dlid;
    private String name;
    private Integer doc_duration;
    private Integer duration;

    public LearnVO(){

    }
    public LearnVO(Integer dlid, String name, Integer doc_duration, Integer duration) {
        this.dlid = dlid;
        this.name = name;
        this.doc_duration = doc_duration;
        this.duration = duration;
    }

    public Integer getDlid() {
        return dlid;
    }

    public LearnVO setDlid(Integer dlid) {
        this.dlid = dlid;
        return this;
    }

    public String getName() {
        return name;
    }

    public LearnVO setName(String name) {
        this.name = name;
        return this;

    }

    public Integer getDoc_duration() {
        return doc_duration;
    }

    public LearnVO setDoc_duration(Integer doc_duration) {
        this.doc_duration = doc_duration;
        return this;

    }

    public Integer getDuration() {
        return duration;
    }

    public LearnVO setDuration(Integer duration) {
        this.duration = duration;
        return this;

    }
}
