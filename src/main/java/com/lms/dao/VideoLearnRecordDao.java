package com.lms.dao;

import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.entity.VideoLearnRecord;
import com.lms.entity.VideoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoLearnRecordDao extends JpaRepository<VideoLearnRecord,Integer> {

    List<VideoLearnRecord> findByUserEntity_Uiid(Long userEntity_uiid);
    Page<VideoLearnRecord> findAll(Pageable pageable);
    VideoLearnRecord findByLrid(Integer lrid);
    VideoLearnRecord findByLridAndUserEntity(Integer lrid, UserEntity userEntity);
    VideoLearnRecord findByVideoListAndUserEntity(VideoList videoList, UserEntity userEntity);

    List<VideoLearnRecord> findAllByVideoList_Vlid(Integer vlid);
}
