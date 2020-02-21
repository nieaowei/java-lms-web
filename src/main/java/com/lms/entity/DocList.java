package com.lms.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name = "doc_list")
@Data
@DynamicUpdate
public class DocList  {
    /**
     * 学习文档ID
     */
    @Id
    @Column(name = "dlid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dlid;
    /**
     * 文档上传人ID，用户表外键
     */
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = "uiid",referencedColumnName = "uiid")
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

    public Integer getDlid() {
        return dlid;
    }

    public void setDlid(Integer dlid) {
        this.dlid = dlid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
}
