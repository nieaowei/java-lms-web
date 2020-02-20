package com.lms.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_info")
@DynamicUpdate
@Data
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class UserEntity {
    /*
    TABLE：使用一个特定的数据库表格来保存主键。
    SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
    IDENTITY：主键由数据库自动生成（主要是自动增长型）
    AUTO：主键由程序控制。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uiid;
    private String username;
    @ColumnTransformer(read = "md5(password)",write = "MD5(?)")
    private String password;
    private String phonenum;
    @Column(insertable = false,updatable = false)
    private Timestamp createtime;
    @Column(insertable = false,updatable = false)
    private Timestamp updatetime;
}
