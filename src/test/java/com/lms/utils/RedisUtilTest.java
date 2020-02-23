package com.lms.utils;

import com.lms.JavaLmsWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JavaLmsWebApplication.class})
@WebAppConfiguration
public class RedisUtilTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testRedis(){
//        RedisUtil redisUtil = new RedisUtil();
//        redisUtil.set("123","321");
//        String str = redisUtil.get("123").toString();
//        System.out.println(str);
        ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
        operations.set("123","321");
    }
}