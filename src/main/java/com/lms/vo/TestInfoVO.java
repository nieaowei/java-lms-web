package com.lms.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

@Data
//@AllArgsConstructor
@JsonSerialize
public class TestInfoVO implements Serializable {
    private Integer tiid;
    /**
     * 文档上传人ID，用户表外键
     */
//    private String username;
    private Integer uiid;
    /**
     * 试题题目
     */
    private String title;
    /**
     * 创建时间
     */
    private String createtime;
    /**
     * 更新时间
     */
    private String updatetime;
    /**
     * 已提交试卷
     */
    private Boolean flag;
    /**
     * 封面图片
     */
    private String cover;

    public String getCover() {
        return cover;
    }

    public TestInfoVO setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public Boolean getFlag() {
        return flag;
    }

    public TestInfoVO setFlag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public Integer getTiid() {
        return tiid;
    }

    public TestInfoVO setTiid(Integer tiid) {
        this.tiid = tiid;
        return this;
    }


    public String getTitle() {
        return title;
    }

    public TestInfoVO setTitle(String title) {
        this.title = title;
        return this;

    }

    public String getCreatetime() {
        return createtime;
    }

    public TestInfoVO setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public TestInfoVO setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public TestInfoVO setUiid(Integer uiid){
        this.uiid = uiid;
        return this;
    }

    public Integer getUiid() {
        return uiid;
    }
}
