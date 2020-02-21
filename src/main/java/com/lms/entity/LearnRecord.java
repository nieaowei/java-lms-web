package com.lms.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name = "learn_record")
@Data
@DynamicUpdate
@IdClass(LearnRecordPK.class)
public class LearnRecord  {
    /**
     * 文档学习记录表id
     */
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lrid;
    /**
     * 文档表记录id，外键
     */
    @Id
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "dlid",referencedColumnName = "dlid")
    private DocList docList;
    /**
     * 学习人id，用户表id
     */
    @Id
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

    public LearnRecord() {
    }
}
