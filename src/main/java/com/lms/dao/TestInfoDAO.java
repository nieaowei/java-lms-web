package com.lms.dao;

import com.lms.entity.TestInfo;
import com.lms.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestInfoDAO extends JpaRepository<TestInfo, Integer> {
    TestInfo findByTiid(Integer tiid);
    TestInfo findByTitle(String title);
    Page<TestInfo> findAll(Pageable pageable);
    List<TestInfo> findByUserEntity(UserEntity userEntity);
    List<TestInfo> findAllByOrderByCreatetimeDesc();
}
