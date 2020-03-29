package com.lms.vo;

import com.lms.entity.TestInfo;
import lombok.Value;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class TestContentVO implements Serializable {
    private Integer tcid;
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
    private Integer type;
    /**
     * 单个选项分值
     */
    private Integer score;

    private String answer;

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOpa(String opa) {
        this.opa = opa;
    }

    public void setOpb(String opb) {
        this.opb = opb;
    }

    public void setOpc(String opc) {
        this.opc = opc;
    }

    public void setOpd(String opd) {
        this.opd = opd;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTcid() {
        return tcid;
    }

    public String getQuestion() {
        return question;
    }

    public String getOpa() {
        return opa;
    }

    public String getOpb() {
        return opb;
    }

    public String getOpc() {
        return opc;
    }

    public String getOpd() {
        return opd;
    }

    public Integer getScore() {
        return score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String i) {
        this.answer = i;
    }
}
