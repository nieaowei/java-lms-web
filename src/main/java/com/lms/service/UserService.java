package com.lms.service;

import com.lms.entity.UserEntity;
import com.lms.dao.UserDAO;
import com.lms.entity.VideoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Resource
    private UserDAO userDAO;

    public UserEntity getByUiidAndPassword(Long uiid , String usernme){
        return userDAO.findByUiidAndPassword(uiid,usernme);
    }

    public boolean isExist(String username) {
        UserEntity user = getByName(username);
        return null!=user;
    }

    public UserEntity findByUiidAndPassword(Long uiid, String password){
        return userDAO.findByUiidAndPassword(uiid, password);
    }

    public UserEntity getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public UserEntity get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public UserEntity findByUiid(Long uiid){return userDAO.findByUiid(uiid);}

    /**
     * 增加，更新可在一起，建议直接更名为save
     * @param user
     */
    public UserEntity save(UserEntity user) {
        return userDAO.save(user);
    }

//    public void delete(UserEntity user){userDAO.delete(user);}

    public boolean isPassWordCompliance(UserEntity user){
        return user.getPassword().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{8,16}$");
    }

    public boolean isPhoneNumCompliance(UserEntity user){
        return user.getPhonenum().matches("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
    }

    public UserEntity getUserByUiid(Long uiid){
        return userDAO.findByUiid(uiid);
    }

    public List<UserEntity> findAll() {
        return userDAO.findAll();
    }
    @Value("${web.default_reset_password}")
    private String defaultPd;
    @Resource
    private EntityManager entityManager;

    public UserEntity updateOne(UserEntity userEntity) {
        UserEntity temp = userDAO.findByUiid(userEntity.getUiid());
        temp.setPassword(defaultPd);
        temp = userDAO.saveAndFlush(temp);
        entityManager.refresh(temp);
        return temp;
    }

    public Integer delete(UserEntity userEntity){
        return userDAO.deleteByUiid(userEntity.getUiid());
    }
}
