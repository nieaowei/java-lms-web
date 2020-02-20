package com.lms.service;

import com.lms.entity.UserEntity;
import com.lms.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public boolean isExist(String username) {
        UserEntity user = getByName(username);
        return null!=user;
    }

    public UserEntity getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public UserEntity get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    /**
     * 增加，更新可在一起，建议直接更名为save
     * @param user
     */
    public void save(UserEntity user) {
        userDAO.save(user);
    }

    public void delete(UserEntity user){userDAO.delete(user);}

}
