package com.lms.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "test_record")
@DynamicUpdate
public class TestRecord {
    /**
     * 考试记录id
     */
    @Id
    @Column(name = "trid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trid;
    /**
     * 考试作答学员，外键
     */
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "uiid",referencedColumnName = "uiid")
    private UserEntity userEntity;

    /**
     * 考试作答学员，外键
     */
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "tiid",referencedColumnName = "tiid")
    private TestInfo testInfo;

    private Integer grade;
    @Column(insertable = false,updatable = false)
    private String createtime;
    @Column(insertable = false,updatable = false)
    private String updatetime;
    @Column(insertable = false,updatable = true)
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public TestRecord setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Integer getTrid() {
        return trid;
    }

    public void setTrid(Integer trid) {
        this.trid = trid;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TestInfo getTestInfo() {
        return testInfo;
    }

    public void setTestInfo(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    public Integer getGrade() {
        return grade;
    }

    public TestRecord setGrade(Integer grade) {
        this.grade = grade;
        return this;
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

    public String getCreatetime() {
        return createtime;
    }
}
