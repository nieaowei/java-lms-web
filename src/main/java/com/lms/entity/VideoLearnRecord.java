package com.lms.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Entity
@Table(name = "video_record")
@DynamicUpdate
public class VideoLearnRecord {
    /**
     * 学习记录表id
     */
    @Id
    @Column(name = "lrid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lrid;
    /**
     * 文档表记录id，外键
     */
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "vlid",referencedColumnName = "vlid")
    private VideoList videoList;
    /**
     * 学习人id，用户表id
     */
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "uiid",referencedColumnName = "uiid")
    private UserEntity userEntity;
    /**
     * 学习时长
     */
    private Integer duration;
    /**
     * 创建时间
     */
    @Column(insertable = false,updatable = false)
    private String createtime;
    /**
     * 更新时间
     */
    @Column(insertable = false,updatable = false)
    private String updatetime;


    public VideoLearnRecord(){
        this.videoList=new VideoList();
        this.userEntity=new UserEntity();
    }

    public Integer getLrid() {
        return lrid;
    }

    public VideoLearnRecord setLrid(Integer lrid) {
        this.lrid = lrid;
        return this;
    }

    public VideoList getVideoList() {
        return videoList;
    }

    public VideoLearnRecord setVideoList(VideoList videoList) {
        this.videoList = videoList;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public VideoLearnRecord setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public VideoLearnRecord setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getCreatetime() {
        return createtime;
    }

    public VideoLearnRecord setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public VideoLearnRecord setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public VideoLearnRecord setUiid(Long uiid){
        this.userEntity.setUiid(uiid);
        return this;
    }

    public VideoLearnRecord setVlid(Integer vlid){
        this.videoList.setVlid(vlid);
        return this;
    }
}
