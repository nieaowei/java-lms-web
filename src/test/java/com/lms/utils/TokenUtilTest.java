package com.lms.utils;

import com.lms.entity.UserEntity;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenUtilTest {

    @Test
    public void getToken() throws InterruptedException {
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername("nieaowei");
        userEntity.setPassword("nieaowei1");
        TokenUtil tokenUtil= new TokenUtil();
        String token = TokenUtil.getTokenForUser((long)123);
        System.out.println(token);
        Thread.sleep(1000);
        System.out.println(TokenUtil.validateTokenUser(token));
    }

    @Test
    public void testGetToken() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse("2020-03-03 16:43:19");
        Date date2 = new Date();
        Date date1 = new Date();
        double timeout = (date1.getTime()-date.getTime())/1000.0/60/60;
    }

    @Test
    public void validateToken() {
    }

    @Test
    public void saveToken() {
    }
}