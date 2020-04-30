package com.lms.service;

import com.lms.dao.TestContentDAO;
import com.lms.dao.TestInfoDAO;
import com.lms.dao.TestRecordDAO;
import com.lms.entity.*;
import com.lms.vo.DocListVO;
import com.lms.vo.TestContentVO;
import com.lms.vo.TestInfoVO;
import com.lms.vo.UserVO;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TestInfoService {
    @Autowired
    private TestInfoDAO testInfoDAO;

    @Autowired
    private TestRecordDAO testRecordDAO;

    @Autowired
    private TestContentDAO testContentDAO;

    @Autowired
    private EntityManager entityManager;

    public TestInfo findByTitle(String title) {
        return testInfoDAO.findByTitle(title);
    }

    public TestInfo findByTiid(Integer tiid) {
        return testInfoDAO.findByTiid(tiid);
    }

    public Page<TestInfo> findAll(Pageable pageable) {
        return testInfoDAO.findAll(pageable);
    }

    public List<TestInfo> findByUserEntity(UserEntity userEntity) {
        return testInfoDAO.findByUserEntity(userEntity);
    }

    public List<TestInfo> findOrder() {
        return testInfoDAO.findAllByOrderByCreatetimeDesc();
    }

    public TestInfo save(TestInfo testInfo) {
        return testInfoDAO.save(testInfo);
    }

    public void delete(Integer tiid) {
        testInfoDAO.deleteById(tiid);
    }

    public List<TestInfoVO> findAllOrderAddFlag(long uiid) {
        List<TestRecord> testRecords = testRecordDAO.findByUserEntity_Uiid(uiid);
        List<TestInfo> testInfoList = findAllOrder();
        List<TestInfoVO> testInfoVOList = new ArrayList<>();
        for (TestInfo testInfo : testInfoList) {
            TestInfoVO testInfoVO = new TestInfoVO().setUsername(testInfo.getUserEntity().getUsername())
                    .setTitle(testInfo.getTitle())
                    .setCreatetime(testInfo.getCreatetime())
                    .setUpdatetime(testInfo.getUpdatetime())
                    .setTiid(testInfo.getTiid())
                    .setFlag(false)
                    .setCover(testInfo.getCover())
                    .setSum(testInfo.getSum())
                    .setNum(testInfo.getNum())
                    .setCount(testRecordDAO.countByTestInfo_Tiid(testInfo.getTiid()));
            int temp = 0;
            for (TestRecord learnRecord : testRecords) {
                if (testInfo.getTiid().equals(learnRecord.getTestInfo().getTiid())) {
                    testInfoVO.setPersoncount(learnRecord.getCount());
                    if (learnRecord.getCount()==0){
                        testInfoVO.setFlag(true);
                    }
                    break;
                }
                temp++;
            }
            if (testRecords.size()==temp){
                testInfoVO.setPersoncount(3);
            }
            testInfoVOList.add(testInfoVO);
        }
        return testInfoVOList;
    }

    private List<TestInfo> findAllOrder() {
        return testInfoDAO.findAllByOrderByCreatetimeDesc();
    }

    public TestInfoVO getTestInfoVOAll(Integer tiid) {
        TestInfo testInfo = testInfoDAO.findByTiid(tiid);
        TestInfoVO testInfoVO = new TestInfoVO();
        BeanUtils.copyProperties(testInfo, testInfoVO);
        List<TestContent> testContents = testContentDAO.findAllByTestInfo_Tiid(tiid);
        List<TestContent> testContents1 = new ArrayList<>();
        for (TestContent testContent : testContents){
            TestContent testContent1 = new TestContent();
            BeanUtils.copyProperties(testContent,testContent1);
            testContent1.setAnswer("");
            testContents1.add(testContent1);
        }
        testInfoVO.setTopics(testContents1);
        return testInfoVO;
    }

    public List<TestInfoVO> findAllForAdmin() {
        List<TestInfo> testInfoList = findAllOrder();
        List<TestInfoVO> docListVOList = new ArrayList<>();
        for (TestInfo testInfo : testInfoList) {
            TestInfoVO testInfoVO = new TestInfoVO();
            BeanUtils.copyProperties(testInfo,testInfoVO);
            BeanUtils.copyProperties(testInfo.getUserEntity(),testInfoVO);
            testInfoVO.setUpdatetime(testInfo.getUpdatetime());
            testInfoVO.setCreatetime(testInfo.getCreatetime());
            docListVOList.add(testInfoVO);
        }
        return docListVOList;
    }

    public TestInfo saveAndRefresh(TestInfo testInfo) {
//        TestInfo testInfo1 = testInfoDAO.findByTiid(testInfo.getTiid());
//        testInfo.setCreatetime(testInfo1.getCreatetime());
//        testInfo.setUpdatetime(testInfo1.getUpdatetime());
        testInfo = testInfoDAO.saveAndFlush(testInfo);
        entityManager.refresh(testInfo);
        return testInfo;
    }

    public TestInfoVO getTestInfoVOAllForAdmin(Integer tiid) {
        TestInfo testInfo = testInfoDAO.findByTiid(tiid);
        TestInfoVO testInfoVO = new TestInfoVO();
        BeanUtils.copyProperties(testInfo, testInfoVO);
        List<TestContent> testContents = testContentDAO.findAllByTestInfo_Tiid(tiid);
        testInfoVO.setTopics(testContents);
        return testInfoVO;
    }

    public TestInfoVO findOneForAdmin(Integer tiid) {
        List<TestRecord> learnRecords = testRecordDAO.findAllByTestInfo_TiidOrderByCreatetimeDesc(tiid);
        if (learnRecords.size() == 0) {
            TestInfo testInfo = testInfoDAO.findByTiid(tiid);
            TestInfoVO testInfoVO = new TestInfoVO();
            BeanUtils.copyProperties(testInfo,testInfoVO);
            return testInfoVO;
        }
        TestInfoVO testInfoVO = this.getTestInfoVOAllForAdmin(tiid);
        BeanUtils.copyProperties(learnRecords.get(0).getTestInfo(),testInfoVO);
        for (TestRecord learnRecord : learnRecords) {
            UserVO userVO = new UserVO(learnRecord.getUserEntity());
            testInfoVO.getUsers().add(userVO.setGrade(learnRecord.getGrade()));
        }
        testInfoVO.setUsername(learnRecords.get(0).getTestInfo().getUserEntity().getUsername());
        return testInfoVO;
    }

    public boolean deleteOneForAdmin(Integer tiid) {
        Integer res = testInfoDAO.deleteByTiid(tiid);
        return res != 0;
    }
}
