package com.lms.utils;

import com.lms.entity.UserEntity;
import org.junit.Test;

public class TokenUtilTest {

    @Test
    public void getToken() {
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername("nieaowei");
        userEntity.setPassword("nieaowei1");
        TokenUtil tokenUtil= new TokenUtil();
//        String token = tokenUtil.getToken(userEntity);
//        System.out.println(token);
    }
}