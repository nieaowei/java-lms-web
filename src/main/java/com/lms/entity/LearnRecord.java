package com.lms.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name = "learn_record")
@DynamicUpdate
public class LearnRecord  {
    /**
     * 文档学习记录表id
     */
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lrid;
    /**
     * 文档表记录id，外键
     */
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "dlid",referencedColumnName = "dlid")
    private DocList docList;
    /**
     * 学习人id，用户表id
     */
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "uiid",referencedColumnName = "uiid")
    private UserEntity userEntity;
    /**
     * 学习时长
     */
    private Integer duration;
    /**
     * 创建时间
     */
    private Timestamp createtime;
    /**
     * 更新时间
     */
    private Timestamp updatetime;

    public Integer getLrid() {
        return lrid;
    }

    public LearnRecord setLrid(Integer lrid) {
        this.lrid = lrid;
        return this;
    }

    public DocList getDocList() {
        return docList;
    }

    public LearnRecord setDocList(DocList docList) {
        this.docList = docList;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LearnRecord setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public LearnRecord setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public LearnRecord setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
        return this;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public LearnRecord setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public LearnRecord setUiid(Long uiid){
        this.userEntity.setUiid(uiid);
        return this;
    }

    public LearnRecord setDlid(Integer dlid){
        this.docList.setDlid(dlid);
        return this;
    }
}
