package com.lms.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "learn_record")
@Data
@DynamicUpdate
public class LearnRecord {
    /**
     * 文档学习记录表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lrid;
    /**
     * 文档表记录id，外键
     */
    @Id
    @JoinColumn(name = "dlid")
    private DocList docList;
    /**
     * 学习人id，用户表id
     */
    @Id
    @JoinColumn(name = "uiid")
    private UserEntity userEntity;
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
