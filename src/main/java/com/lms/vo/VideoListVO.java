package com.lms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VideoListVO implements Serializable {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public VideoListVO setCount(Integer count) {
        this.count = count;
        return this;
    }

    public void setUsers(List<UserVO> users) {
        this.users = users;
    }

    private Integer vlid;
    /**
     * 视频上传人ID，用户表外键
     */
    private String username;
//    private Integer uiid;
    /**
     * 视频名称
     */
    private String name;
    /**
     * 学习时长
     */
    private Integer duration;
    /**
     * 创建时间
     */
    private String createtime;
    /**
     * 更新时间
     */
    private String updatetime;
    /**
     * 用于前端已学视频辨识
     */
    private Boolean flag;
    /**
     * 封面图片
     */
    private String cover;

    private String path;

    private List<UserVO> users;

    public List<UserVO> getUsers() {
        return users;
    }
    public VideoListVO(){
        this.users=new ArrayList<>();
    }

    public String getCover() {
        return cover;
    }

    public VideoListVO setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public Boolean getFlag() {
        return flag;
    }

    public VideoListVO setFlag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public Integer getVlid() {
        return vlid;
    }

    public VideoListVO setVlid(Integer vlid) {
        this.vlid = vlid;
        return this;
    }


    public String getName() {
        return name;
    }

    public VideoListVO setName(String name) {
        this.name = name;
        return this;

    }
    public Integer getDuration() {
        return duration;
    }

    public VideoListVO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getCreatetime() {
        return createtime;
    }

    public VideoListVO setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public VideoListVO setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public VideoListVO setUsername(String username){
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPath() {
        return path;
    }

    public VideoListVO setPath(String path) {
        this.path=path;
        return this;
    }
}
