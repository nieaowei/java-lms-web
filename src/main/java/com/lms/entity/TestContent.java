package com.lms.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "test_content")
@DynamicUpdate
public class TestContent {
    /**
     * 试卷ID
     */
    @Id
    @Column(name = "tcid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tcid;
    /**
     * 文档上传人ID，用户表外键
     */
    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = "tiid",referencedColumnName = "tiid")
    private TestInfo testInfo;
    /**
     * 试题问题
     */
    private String question;
    /**
     * 选线a,b,c,d
     */
    private String opa;
    private String opb;
    private String opc;
    private String opd;
    /**
     * 试题答案
     */
    private Integer answer;
    /**
     * 单个选项分值
     */
    private Integer score;
    private String createtime;
    private String updatetime;

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public TestInfo getTestInfo() {
        return testInfo;
    }

    public void setTestInfo(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpa() {
        return opa;
    }

    public void setOpa(String opa) {
        this.opa = opa;
    }

    public String getOpb() {
        return opb;
    }

    public void setOpb(String opb) {
        this.opb = opb;
    }

    public String getOpc() {
        return opc;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    public String getOpd() {
        return opd;
    }

    public void setOpd(String opd) {
        this.opd = opd;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
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

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}
