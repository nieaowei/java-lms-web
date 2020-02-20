package com.lms.dao;

import com.lms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
    UserEntity getByUsernameAndPassword(String username,String password);
}
