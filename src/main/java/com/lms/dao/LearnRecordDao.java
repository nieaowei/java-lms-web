package com.lms.dao;

import com.lms.entity.LearnRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnRecordDao extends JpaRepository<LearnRecord, Integer> {
    LearnRecord findByUiid(Integer uuid);
    Page<LearnRecord> findAll(Pageable pageable);

}
