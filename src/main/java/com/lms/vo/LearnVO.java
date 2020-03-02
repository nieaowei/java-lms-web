package com.lms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * 学习记录业务模型
 *
 */

@Data
//@AllArgsConstructor
@JsonSerialize
public class LearnVO implements Serializable {
    private Integer dlid;//课程id
    private String name;//课程名称
    private Integer doc_duration;//课程时间
    private Integer duration;//已学习时间
    private Boolean finished;//完成标志
    private Double percent;//百分比

    public LearnVO computedPercent() {
        if (this.duration == null) {
            return this;
        }
        if (this.doc_duration == null) {
            return this;
        }
        this.percent = ((double) (this.duration) / (double) (this.doc_duration)) * 100;
        return this;
    }

    public Double getPercent() {
        return percent;
    }

    public LearnVO setPercent(Double percent) {
        this.percent = percent;
        return this;
    }

    public LearnVO isFinished() {
        this.finished = false;
        if (this.duration == null) {
            return this;
        }
        if (this.doc_duration == null) {
            return this;
        }
        if (this.doc_duration.equals(this.duration)) {
            this.finished = true;
        }
        return this;
    }

    public Boolean getFinished() {
        return finished;
    }

    public LearnVO setFinished(Boolean finished) {
        this.finished = finished;
        return this;
    }

    public LearnVO() {

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
