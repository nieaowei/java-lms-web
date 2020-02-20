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
    @ColumnTransformer(write = "MD5(?)")
    private String password;
    private String phonenum;
    @Column(insertable = false,updatable = false)
    private Timestamp createtime;
    @Column(insertable = false,updatable = false)
    private Timestamp updatetime;

    public Long getUiid() {
        return uiid;
    }

    public void setUiid(Long uiid) {
        this.uiid = uiid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
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
