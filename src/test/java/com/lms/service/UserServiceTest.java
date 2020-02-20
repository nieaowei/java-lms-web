package com.lms.service;
import com.lms.entity.UserEntity;
import com.lms.JavaLmsWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JavaLmsWebApplication.class})
@WebAppConfiguration
public class UserServiceTest {
    @Autowired
    private UserService userService;//new UserService();
    @Test
    public void add() {
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername("nieaowei5555");
        userEntity.setPassword("12345555");
        userEntity.setPhonenum("13879790909");
        userService.add(userEntity);
        System.out.println(userEntity.getUsername());
    }
}