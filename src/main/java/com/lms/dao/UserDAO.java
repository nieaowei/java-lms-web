package com.lms.dao;

import com.lms.entity.UserEntity;
import com.lms.vo.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDAO extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
    @Query(value="select * from user_info where username=?1 and password=md5(?2)",nativeQuery = true)
    UserEntity getByUsernameAndPassword(String username,String password);
    UserEntity findByUiid(Long uiid);
    @Query(value="select * from user_info where uiid=?1 and password=md5(?2)",nativeQuery = true)
    UserEntity findByUiidAndPassword(Long uiid, String password);
    UserDTO findByPhonenum(String phonenum);
//    @Update("update user_info set password")
//    Integer updateUserPasswordByUiid(Long uiid,String password);
    Integer deleteByUiid(Long uiid);
}
