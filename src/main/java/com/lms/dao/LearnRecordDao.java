package com.lms.dao;

import com.lms.entity.LearnRecord;
import com.lms.vo.LearnVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LearnRecordDao extends JpaRepository<LearnRecord, Integer> {
    LearnRecord findByUiid(Integer uuid);
    Page<LearnRecord> findAll(Pageable pageable);
    @Query(nativeQuery = true, value = "select d.dlid,d.name,d.duration,l.duration from doc_list d ,learn_record l where l.dlid = d.dlid")
    List<LearnVO> findAllLearnVO();
    @Query(nativeQuery = true, value = "select d.name,d.duration,l.duration from learn_record l,doc_list d where name = ?1 and l.dlid = d.dlid")
    LearnVO findLearnVoByName(String name);
}
