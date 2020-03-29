package com.lms.entity;

import com.lms.vo.UserDTO;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "doc_list")
@DynamicUpdate
public class DocList  {
    /**
     * 学习文档ID
     */
    @Id
    @Column(name = "dlid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dlid;
    /**
     * 文档上传人ID，用户表外键
     */
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "uiid",referencedColumnName = "uiid")
    private UserEntity userEntity;
//    private Integer uiid;
    /**
     * 用户名
     */
    private String name;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 封面图片位置
     */
    private String cover;
    /**
     * 学习时长
     */
    private Integer duration;
    /**
     * 创建时间
     */
    @Column(insertable = false,updatable = false)
    private String createtime;
    /**
     * 更新时间
     */
    @Column(insertable = false,updatable = false)
    private String updatetime;

    public String getCover() {
        if("".equals(cover)){
            return null;
        }
        return cover;
    }

    public DocList setCover(String cover) {
        this.cover = cover;
        if("".equals(cover)){
            this.cover=null;
        }
        return this;
    }

    public Integer getDlid() {
        return dlid;
    }

    public DocList setDlid(Integer dlid) {
        this.dlid = dlid;
        return this;
    }


    public String getName() {
        if("".equals(name)){
            return null;
        }
        return name;
    }

    public DocList setName(String name) {
        this.name = name;
        if("".equals(name)){
            this.name=null;
        }
        return this;

    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getPath() {
        if("".equals(path)){
            return null;
        }
        return path;
    }

    public DocList setPath(String path) {
        this.path = path;
        if("".equals(path)){
            this.path=null;
        }
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public DocList setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getCreatetime() {
        return createtime;
    }

    public DocList setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public DocList setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
        return this;
    }

    public DocList setUiid(Long uiid){
        if (this.userEntity==null){
            this.userEntity= new UserEntity();
        }
        this.userEntity.setUiid(uiid);
        return this;
    }

    public String getUsername(){
        return this.userEntity.getUsername();
    }

    public DocList setUsername(String username){
        this.userEntity.setUsername(username);
        return this;
    }

    public UserDTO getUserDTO(){
        return (UserDTO) this.userEntity;
    }
}
