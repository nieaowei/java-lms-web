package com.lms.service;

import com.lms.dao.TestRecordDAO;
import com.lms.entity.LearnRecord;
import com.lms.entity.TestInfo;
import com.lms.entity.TestRecord;
import com.lms.entity.UserEntity;
import com.lms.vo.TestRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TestRecordService {

    @Autowired
    private TestRecordDAO testRecordDAO;

    @Autowired
    private EntityManager entityManager;

    public List<TestRecord> findAllByUserEntity(UserEntity userEntity){
        return testRecordDAO.findAllByUserEntity(userEntity);
    }

    public TestRecord findByUserEntityAndTestInfo(UserEntity userEntity, TestInfo testInfo){
        return testRecordDAO.findByUserEntityAndTestInfo(userEntity, testInfo);
    }

    public List<TestRecordVO> findVOByUiid(Long uiid){
        List<TestRecord> testRecords = testRecordDAO.findByUserEntity_Uiid(uiid);
        List<TestRecordVO> testRecordVOS = new ArrayList<>();
        for (TestRecord testRecord: testRecords) {
            TestRecordVO testRecordVO = new TestRecordVO();
            BeanUtils.copyProperties(testRecord,testRecordVO);
            BeanUtils.copyProperties(testRecord.getTestInfo(),testRecordVO);
            testRecordVOS.add(testRecordVO);
        }
        return testRecordVOS;
    }

    public TestRecord save(TestRecord testRecord){
        return testRecordDAO.save(testRecord);
    }

    public TestRecord saveAndRefresh(TestRecord learnRecord){
        learnRecord = testRecordDAO.save(learnRecord);
        entityManager.refresh(learnRecord);
        return learnRecord;
    }
}
