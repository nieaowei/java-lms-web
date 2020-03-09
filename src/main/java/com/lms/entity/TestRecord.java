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

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
