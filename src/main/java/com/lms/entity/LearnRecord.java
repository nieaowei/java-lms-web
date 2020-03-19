package com.lms.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "learn_record")
@DynamicUpdate
public class LearnRecord  {
    /**
     * 文档学习记录表id
     */
    @Id
    @Column(name = "lrid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lrid;
    /**
     * 文档表记录id，外键
     */
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "dlid",referencedColumnName = "dlid")
    private DocList docList;
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
    private String createtime;
    /**
     * 更新时间
     */
    private String updatetime;

    public LearnRecord(){
        this.docList=new DocList();
        this.userEntity=new UserEntity();
    }

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

    public String getCreatetime() {
        return createtime;
    }

    public LearnRecord setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public LearnRecord setUpdatetime(String updatetime) {
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
