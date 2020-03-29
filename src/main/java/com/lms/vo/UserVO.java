package com.lms.vo;

import com.lms.entity.UserEntity;

import java.io.Serializable;

public class UserVO implements Serializable {
    private String username;
    private Long uiid;
    private Boolean admin;
    private Integer vduration;
    private Integer dduration;
    private Integer grade;

    public Integer getVduration() {
        return vduration;
    }

    public UserVO setVduration(Integer vduration) {
        this.vduration = vduration;
        return this;
    }

    public Integer getDduration() {
        return dduration;
    }

    public UserVO setDduration(Integer dduration) {
        this.dduration = dduration;
        return this;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public UserVO(){

    }

    public UserVO(UserEntity userEntity){
        this.username = userEntity.getUsername();
        this.uiid = userEntity.getUiid();
        this.admin = userEntity.getAdmin();
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getUiid() {
        return uiid;
    }

    public void setUiid(Long uiid) {
        this.uiid = uiid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
