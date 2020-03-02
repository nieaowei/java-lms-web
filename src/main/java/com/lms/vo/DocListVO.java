package com.lms.vo;

/***
 *
 * 文档列表业务数据模型
 *
 */

public class DocListVO {
    private Integer dlid;
    /**
     * 文档上传人ID，用户表外键
     */
    private String username;
//    private Integer uiid;
    /**
     * w文档名称
     */
    private String name;
    /**
     * 学习时长
     */
    private Integer duration;
    /**
     * 创建时间
     */
    private String createtime;
    /**
     * 更新时间
     */
    private String updatetime;
    /**
     * 用于前端已学课程辨识
     */
    private Boolean flag;

    public Boolean getFlag() {
        return flag;
    }

    public DocListVO setFlag(Boolean flag) {
        this.flag = flag;
        return this;
    }

    public Integer getDlid() {
        return dlid;
    }

    public DocListVO setDlid(Integer dlid) {
        this.dlid = dlid;
        return this;
    }


    public String getName() {
        return name;
    }

    public DocListVO setName(String name) {
        this.name = name;
        return this;

    }
    public Integer getDuration() {
        return duration;
    }

    public DocListVO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getCreatetime() {
        return createtime;
    }

    public DocListVO setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public DocListVO setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public DocListVO setUsername(String username){
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }
}