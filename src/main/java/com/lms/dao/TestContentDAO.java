package com.lms.dao;

import com.lms.entity.TestContent;
import com.lms.entity.TestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestContentDAO extends JpaRepository<TestContent, Integer> {
    List<TestContent> findAllByTestInfo(TestInfo testInfo);
    TestContent findByTcid(Integer tcid);
    TestContent findByTcidAndAnswer(Integer tcid, String answer);
    Integer countAllByTcid(Integer tcid);
    List<TestContent> findAllByTestInfo_Tiid(Integer testInfo_tiid);
}
