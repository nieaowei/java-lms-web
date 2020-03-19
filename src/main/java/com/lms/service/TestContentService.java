package com.lms.service;

import com.lms.dao.TestContentDAO;
import com.lms.entity.TestContent;
import com.lms.entity.TestInfo;
import com.lms.entity.TestRecord;
import com.lms.vo.TestContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestContentService {

    @Autowired
    private TestContentDAO testContentDAO;

    public List<com.lms.entity.TestContent> findAllByTestInfo(TestInfo testInfo){
        return testContentDAO.findAllByTestInfo(testInfo);
    }

    public com.lms.entity.TestContent save(com.lms.entity.TestContent testContent){
        return testContentDAO.save(testContent);
    }

    public Integer countAllByTcid(Integer tcid){
        return testContentDAO.countAllByTcid(tcid);
    }

    public TestRecord answer(List<TestContent> testContentVOS){
        TestRecord testRecord = new TestRecord().setGrade(0);
        for (TestContent testContentVO:testContentVOS) {
            TestContent testContent= testContentDAO.findByTcidAndAnswer(testContentVO.getTcid(),testContentVO.getAnswer());
            if (testContent!=null){
                testRecord.setGrade(testRecord.getGrade()+testContent.getScore());
            }
        }
        return testRecord;
    }

    public void saveAll(List<TestContent> topics) {
        testContentDAO.saveAll(topics);
    }
}
