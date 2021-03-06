package com.lms.vo;

import com.lms.entity.DocList;
import com.lms.entity.UserEntity;
import com.sun.imageio.plugins.common.I18N;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * 文档列表业务数据模型
 *
 */

public class DocListVO implements Serializable {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public DocListVO setCount(Integer count) {
        this.count = count;
        return this;
    }

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
    /**
     * 封面图片
     */
    private String cover;

    private List<UserVO> users;

    private String path;

    public String getPath() {
        return path;
    }

    public DocListVO setPath(String path) {
        this.path = path;
        return this;
    }

    public List<UserVO> getUsers() {
        return users;
    }

    public void setUsers(List<UserVO> users) {
        this.users = users;
    }

    public DocListVO(){
        this.users=new ArrayList<>();
    }

    public void initUsers(){
        this.users = new ArrayList<>();
    }

    public DocListVO(DocList docList){
        this.initUsers();
        this.setUsername(docList.getUserEntity().getUsername())
                .setFlag(false)
                .setCover(docList.getCover())
                .setDuration(docList.getDuration())
                .setDlid(docList.getDlid())
                .setCreatetime(docList.getCreatetime())
                .setUpdatetime(docList.getUpdatetime())
                .setName(docList.getName())
                .setPath(docList.getPath());
    }

    public String getCover() {
        return cover;
    }

    public DocListVO setCover(String cover) {
        this.cover = cover;
        return this;
    }

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
