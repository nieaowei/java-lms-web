package com.lms.service;

import com.lms.JavaLmsWebApplication;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JavaLmsWebApplication.class})
@WebAppConfiguration
public class LearnRecordServiceTest {
    @Autowired
    LearnRecordService learnRecordService;
    @Test
    public void findByUiid() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUiid(new Long(111));
        List<LearnRecord> learnRecords=learnRecordService.findByUiid(userEntity);
        for (int i=0;i<learnRecords.size();i++){
            System.out.println(learnRecords.get(i));
        }
    }
}