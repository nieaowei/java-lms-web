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

    List<VideoLearnRecord> findByUserEntity(UserEntity userEntity);
    Page<VideoLearnRecord> findAll(Pageable pageable);
    VideoLearnRecord findByLrid(Integer lrid);
    VideoLearnRecord findByLridAndUserEntity(Integer lrid, UserEntity userEntity);
    VideoLearnRecord findByVideoListAndUserEntity(VideoList videoList, UserEntity userEntity);
    @Query(value="insert into video_record(uiid, vlid) values(?1,?2)",nativeQuery = true)
    VideoLearnRecord insert(Integer uiid, Integer vlid);
    @Query(value = "update video_record set duration=?3 where uiid=?1 and vlid=?2",nativeQuery=true)
    VideoLearnRecord update(Long uiid, Integer vlid, Integer duration);
}
