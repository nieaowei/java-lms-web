package com.lms.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lms.entity.TestContent;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
//@AllArgsConstructor
@JsonSerialize
public class TestInfoVO implements Serializable {
    private Integer tiid;
    /**
     * 文档上传人ID，用户表外键
     */
    private String username;
//    private Integer uiid;
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
    /**
     * 题目列表
     */
    private List<TestContent> topics;

    private List<UserVO> Users;


    private Integer sum;

    private Integer num;



    public List<UserVO> getUsers() {
        if (this.Users ==null){
            this.Users = new ArrayList<>();
        }
        return Users;
    }

    public void setUsers(List<UserVO> users) {
        if (this.Users ==null){
            this.Users = new ArrayList<>();
        }
        this.Users = users;
    }

    public Integer getNum() {
        return num;
    }

    public TestInfoVO setNum(Integer num) {
        this.num = num;
        return this;
    }

    public Integer getSum() {
        return sum;
    }

    public TestInfoVO setSum(Integer sum) {
        this.sum = sum;
        return this;
    }

    public List<TestContent> getTopics() {
        return topics;
    }

    public void setTopics(List<TestContent> topics) {
        this.topics = topics;
    }

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

    public String getUsername() {
        return username;
    }

    public TestInfoVO setUsername(String username) {
        this.username = username;
        return this;
    }
}
