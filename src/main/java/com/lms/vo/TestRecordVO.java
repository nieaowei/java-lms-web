package com.lms.vo;

public class TestRecordVO {
    Integer tiid;
    String title;
    Integer sum;
    Integer grade;
    String createtime;
    String cover;

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

    public String getCreatetime() {
        return createtime;

    }

    public TestRecordVO setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }
}
