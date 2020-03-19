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
    /**
     * 单个选项分值
     */
    private Integer score;

    private Integer answer;


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

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(int i) {
        this.answer = i;
    }
}
