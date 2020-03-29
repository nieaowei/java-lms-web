package com.lms.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "test_info")
@DynamicUpdate
@DynamicInsert
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uiid",referencedColumnName = "uiid")
    private UserEntity userEntity;
    /**
     * 试题名称
     */
    private String title;

    /**
     * 封面地址
     */
    private String cover;

    /**
     * 总分
     */
    private Integer sum;
    /**
     * 试题数量
     */
    private Integer num;
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


    public Integer getNum() {
        return num;
    }

    public TestInfo setNum(Integer num) {
        this.num = num;
        return this;
    }

    public Integer getSum() {
        return sum;
    }

    public TestInfo setSum(Integer sum) {
        this.sum = sum;
        return this;
    }

    public String getCover() {
        if("".equals(cover)){
            return null;
        }
        return cover;
    }

    public TestInfo setCover(String cover) {
        this.cover = cover;
        if ("".equals(cover)){
            this.cover=null;
        }
        return this;
    }

    public Integer getTiid() {
        return tiid;
    }

    public TestInfo setTiid(Integer tiid) {
        this.tiid = tiid;
        return this;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getTitle() {
        if("".equals(title)){
            return null;
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if ("".equals(title)){
            this.title=null;
        }
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
