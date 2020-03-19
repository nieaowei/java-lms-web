package com.lms.vo;

import com.lms.entity.UserEntity;

public class UserVO {
    private String username;
    private Long uiid;

    public UserVO(){

    }

    public UserVO(UserEntity userEntity){
        this.username = userEntity.getUsername();
        this.uiid = userEntity.getUiid();
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
