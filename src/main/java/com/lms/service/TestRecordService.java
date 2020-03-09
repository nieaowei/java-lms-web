package com.lms.service;

import com.lms.dao.TestRecordDAO;
import com.lms.entity.TestInfo;
import com.lms.entity.TestRecord;
import com.lms.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestRecordService {

    @Autowired
    private TestRecordDAO testRecordDAO;

    public List<TestRecord> findAllByUserEntity(UserEntity userEntity){
        return testRecordDAO.findAllByUserEntity(userEntity);
    }

    public TestRecord findByUserEntityAndTestInfo(UserEntity userEntity, TestInfo testInfo){
        return testRecordDAO.findByUserEntityAndTestInfo(userEntity, testInfo);
    }

    public TestRecord save(TestRecord testRecord){
        return testRecordDAO.save(testRecord);
    }
}
