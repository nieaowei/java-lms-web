package com.lms.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lms.entity.LearnRecord;
import com.lms.entity.VideoLearnRecord;
import lombok.Data;

import java.io.Serializable;

@Data
//@AllArgsConstructor
@JsonSerialize
public class VideoLearnVO implements Serializable {
    private Integer vlid;//视频id
    private String name;//视频名称
    private Integer doc_duration;//视频时间
    private String path;
    private Integer duration;//已学习时间
    private Boolean finished;//完成标志
    private Double percent;//百分比

    /**
     * 封面图片
     */
    private String cover;

    public VideoLearnVO(VideoLearnRecord learnRecord){
        this.setName(learnRecord.getVideoList().getName())
                .setVlid(learnRecord.getVideoList().getVlid())
                .setDoc_duration(learnRecord.getVideoList().getDuration())
                .setDuration(learnRecord.getDuration())
                .isFinished()
                .computedPercent()
                .setPath(learnRecord.getVideoList().getPath())
                .setCover(learnRecord.getVideoList().getCover());
    }

    public String getCover() {
        return cover;
    }

    public VideoLearnVO setCover(String cover) {
        this.cover = cover;
        return this;
    }
    public String getPath() {
        return path;
    }

    public VideoLearnVO setPath(String path) {
        this.path = path;
        return this;
    }

    public VideoLearnVO computedPercent() {
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

    public VideoLearnVO setPercent(Double percent) {
        this.percent = percent;
        return this;
    }

    public VideoLearnVO isFinished() {
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

    public VideoLearnVO setFinished(Boolean finished) {
        this.finished = finished;
        return this;
    }

    public VideoLearnVO() {

    }

    public VideoLearnVO(Integer dlid, String name, Integer doc_duration, Integer duration) {
        this.vlid = dlid;
        this.name = name;
        this.doc_duration = doc_duration;
        this.duration = duration;
    }

    public Integer getVlid() {
        return vlid;
    }

    public VideoLearnVO setVlid(Integer vlid) {
        this.vlid = vlid;
        return this;
    }

    public String getName() {
        return name;
    }

    public VideoLearnVO setName(String name) {
        this.name = name;
        return this;

    }

    public Integer getDoc_duration() {
        return doc_duration;
    }

    public VideoLearnVO setDoc_duration(Integer doc_duration) {
        this.doc_duration = doc_duration;
        return this;

    }

    public Integer getDuration() {
        return duration;
    }

    public VideoLearnVO setDuration(Integer duration) {
        this.duration = duration;
        return this;

    }
}
