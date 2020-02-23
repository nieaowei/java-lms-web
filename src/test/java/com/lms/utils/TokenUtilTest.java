package com.lms.utils;

import com.lms.entity.Token;
import com.lms.entity.UserEntity;
import org.junit.Test;

public class TokenUtilTest {

    @Test
    public void getToken() throws InterruptedException {
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername("nieaowei");
        userEntity.setPassword("nieaowei1");
        TokenUtil tokenUtil= new TokenUtil();
        String token = TokenUtil.getToken((long)123);
        System.out.println(token);
        Thread.sleep(1000);
        System.out.println(TokenUtil.validateToken(token));
    }

    @Test
    public void testGetToken() {
    }

    @Test
    public void validateToken() {
    }

    @Test
    public void saveToken() {
    }
}