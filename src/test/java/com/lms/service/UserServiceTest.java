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
        //todo dada
        UserEntity userEntity=new UserEntity();
//        userEntity.setUsername("nieaowei1555");
//        userEntity.setPassword("12345555");
//        userEntity.setPhonenum("13879700109");
//        userService.save(userEntity);
//        System.out.println(userEntity.getUsername());
        userEntity=userService.get("nieaowei1234","nieaowei");
        if (null != userEntity){
            System.out.println(userEntity.getUsername());
        }else{
            System.out.println("falid");
        }

    }
}