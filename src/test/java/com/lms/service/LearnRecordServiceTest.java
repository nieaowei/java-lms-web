package com.lms.service;

import com.lms.JavaLmsWebApplication;
import com.lms.dao.LearnRecordDao;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.vo.LearnVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {JavaLmsWebApplication.class})
@WebAppConfiguration
public class LearnRecordServiceTest {
    @Autowired
    LearnRecordService learnRecordService;
    @Autowired
    LearnRecordDao learnRecordDao;
    @Test
    public void findByUiid() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUiid(new Long(103));
//        List<LearnVO> learnRecords=learnRecordService.findAllLearnVO(103L);
//        for (int i=0;i<learnRecords.size();i++){
//            System.out.println(learnRecords.get(i));
//        }
//        List<LearnRecord> learnRecord=learnRecordDao.findLearnRecordsByUserEntity(userEntity);
//        System.out.println(learnRecord.get(0).getUserEntity().getUsername());
    }

    @Test
    public void updateDuration() {
    }
}