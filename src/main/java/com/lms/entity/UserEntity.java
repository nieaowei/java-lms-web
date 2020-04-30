package com.lms.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@DynamicUpdate
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class UserEntity   {
    /*
    TABLE：使用一个特定的数据库表格来保存主键。
    SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
    IDENTITY：主键由数据库自动生成（主要是自动增长型）
    AUTO：主键由程序控制。
     */
    @Id
    @Column(name ="uiid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uiid;
    private String username;
    @ColumnTransformer(write = "MD5(?)")
    private String password;
    private String phonenum;
    @Column(insertable = false,updatable = false)
    private String createtime;
    @Column(insertable = false,updatable = false)
    private String updatetime;
    private Boolean admin;

    public Boolean getAdmin() {
        return admin;
    }

    public UserEntity setAdmin(Boolean admin) {
        this.admin = admin;
        return this;
    }

    public Long getUiid() {
        return uiid;
    }

    public UserEntity setUiid(Long uiid) {
        this.uiid = uiid;
        return this;

    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;

    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;

    }

    public String getPhonenum() {
        return phonenum;
    }

    public UserEntity setPhonenum(String phonenum) {
        this.phonenum = phonenum;
        return this;

    }

    public String getCreatetime() {
        return createtime;
    }

    public UserEntity setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;

    }

    public String getUpdatetime() {
        return updatetime;
    }

    public UserEntity setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;

    }
}
