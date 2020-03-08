package com.lms.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "video_list")
@DynamicUpdate
public class VideoList {
    /**
     * 学习文档ID
     */
    @Id
    @Column(name = "vlid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vlid;
    /**
     * 文档上传人ID，用户表外键
     */
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "uiid", referencedColumnName = "uiid")
    private UserEntity userEntity;
//    private Integer uiid;
    /**
     * 用户名
     */
    private String name;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 封面图片位置
     */
    private String cover;
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

    public String getCover() {
        return cover;
    }

    public VideoList setCover(String cover) {
        this.cover = cover;
        return this;
    }

    public Integer getVlid() {
        return vlid;
    }

    public VideoList setVlid(Integer vlid) {
        this.vlid = vlid;
        return this;
    }


    public String getName() {
        return name;
    }

    public VideoList setName(String name) {
        this.name = name;
        return this;

    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getPath() {
        return path;
    }

    public VideoList setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public VideoList setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getCreatetime() {
        return createtime;
    }

    public VideoList setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public VideoList setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public VideoList setUiid(Long uiid) {
        this.userEntity.setUiid(uiid);
        return this;
    }

}