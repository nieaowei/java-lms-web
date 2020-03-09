package com.lms.service;

import com.lms.dao.TestContentDAO;
import com.lms.entity.TestContent;
import com.lms.entity.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestContentService {

    @Autowired
    private TestContentDAO testContentDAO;

    public List<TestContent> findAllByTestInfo(TestInfo testInfo){
        return testContentDAO.findAllByTestInfo(testInfo);
    }

    public TestContent save(TestContent testContent){
        return testContentDAO.save(testContent);
    }

    public Integer countAllByTcid(){
        return testContentDAO.countAllByTcid();
    }
}
