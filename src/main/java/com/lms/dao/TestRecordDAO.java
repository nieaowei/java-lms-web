package com.lms.dao;

import com.lms.entity.TestInfo;
import com.lms.entity.TestRecord;
import com.lms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRecordDAO extends JpaRepository<TestRecord, Integer> {
    List<TestRecord> findAllByUserEntity(UserEntity userEntity);
    TestRecord findByUserEntityAndTestInfo(UserEntity userEntity, TestInfo testInfo);
}
