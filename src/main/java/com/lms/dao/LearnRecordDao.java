package com.lms.dao;

import com.lms.entity.DocList;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LearnRecordDao extends JpaRepository<LearnRecord, Integer> {
    List<LearnRecord> findByUserEntity_UiidOrderByCreatetimeDesc(Long userEntity_uiid);
    Page<LearnRecord> findAll(Pageable pageable);
    LearnRecord findByLrid(Integer lrid);

    LearnRecord findByDocList_DlidAndUserEntity_Uiid(Integer docList_dlid, Long userEntity_uiid);

    @Modifying
    @Query(value="insert into learn_record (uiid,dlid) values(?1,?2)",nativeQuery = true)
    LearnRecord insert(Long uiid,Integer dlid);
    @Query(value = "update learn_record set duration=?3 where uiid=?1 and dlid=?2",nativeQuery=true)
    LearnRecord update(Long uiid,Integer dlid,Integer duration);

    List<LearnRecord> findAllByDocList_Dlid(Integer docList_dlid);
}
