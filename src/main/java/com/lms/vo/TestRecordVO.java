package com.lms.vo;

import java.io.Serializable;

public class TestRecordVO implements Serializable {
    Integer tiid;
    String title;
    Integer sum;
    Integer grade;
    String updatetime;
    String cover;

    Integer personcount;

    public Integer getPersoncount() {
        return personcount;
    }

    public TestRecordVO setPersoncount(Integer personcount) {
        this.personcount = personcount;

        return this;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getTiid() {
        return tiid;
    }

    public TestRecordVO setTiid(Integer tiid) {
        this.tiid = tiid;
        return this;

    }

    public String getTitle() {
        return title;
    }

    public TestRecordVO setTitle(String title) {
        this.title = title;
        return this;

    }

    public Integer getSum() {
        return sum;
    }

    public TestRecordVO setSum(Integer sum) {
        this.sum = sum;
        return this;

    }

    public Integer getGrade() {
        return grade;
    }

    public TestRecordVO setGrade(Integer grade) {
        this.grade = grade;
        return this;

    }

    public String getUpdatetime() {
        return updatetime;

    }

    public TestRecordVO setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;
    }
}
