package com.lms.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "doc_list")
@Data
@DynamicUpdate
public class DocList {
    /**
     * 学习文档ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dlid;
    /**
     * 文档上传人ID，用户表外键
     */
    @Id
    @JoinColumn(name = "uiid")
    private UserEntity userEntity;
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
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
}
