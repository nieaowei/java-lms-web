package com.lms.service;

import com.lms.dao.TestInfoDAO;
import com.lms.entity.TestInfo;
import com.lms.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestInfoService {
    @Autowired
    private TestInfoDAO testInfoDAO;

    public TestInfo findByTitle(String title){return testInfoDAO.findByTitle(title);}

    public TestInfo findByTiid(Integer tiid){return testInfoDAO.findByTiid(tiid);}

    public Page<TestInfo> findAll(Pageable pageable){return testInfoDAO.findAll(pageable);}

    public List<TestInfo> findByUserEntity(UserEntity userEntity){return testInfoDAO.findByUserEntity(userEntity);}

    public List<TestInfo> findOrder(){return testInfoDAO.findAllByOrderByCreatetimeDesc();}

    public TestInfo save(TestInfo testInfo){return testInfoDAO.save(testInfo);}

    public void delete(Integer tiid){ testInfoDAO.deleteById(tiid);}
}
