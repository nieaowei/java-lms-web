package com.lms.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "test_info")
@DynamicUpdate
public class TestInfo {
    /**
     * 试卷ID
     */
    @Id
    @Column(name = "tiid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tiid;
    /**
     * 文档上传人ID，用户表外键
     */
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = "uiid",referencedColumnName = "uiid")
    private UserEntity userEntity;
    /**
     * 试题名称
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

    public Integer getTiid() {
        return tiid;
    }

    public void setTiid(Integer tiid) {
        this.tiid = tiid;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
