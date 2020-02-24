package com.lms.dao;

import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.vo.LearnVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LearnRecordDao extends JpaRepository<LearnRecord, Integer> {
    List<LearnRecord> findByUserEntity(UserEntity uuid);
    Page<LearnRecord> findAll(Pageable pageable);

}
