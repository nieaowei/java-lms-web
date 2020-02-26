package com.lms.service;

import com.lms.entity.UserEntity;
import com.lms.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
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

    public void delete(UserEntity user){userDAO.delete(user);}

    public boolean isPassWordCompliance(UserEntity user){
        return user.getPassword().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{8,16}$")?true:false;
    }

    public boolean isPhoneNumCompliance(UserEntity user){
        return user.getPhonenum().matches("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$")?true:false;
    }

}
