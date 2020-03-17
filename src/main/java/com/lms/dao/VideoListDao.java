package com.lms.dao;

import com.lms.entity.DocList;
import com.lms.entity.UserEntity;
import com.lms.entity.VideoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoListDao extends JpaRepository<VideoList,Integer> {

    VideoList findByName(String name);
    VideoList findByVlid(Integer vlid);
    Page<VideoList> findAll(Pageable pageable);
    List<VideoList> findByUserEntity(UserEntity uiid);
    List<VideoList> findAllByOrderByCreatetimeDesc();

    Integer deleteVideoListByVlid(Integer vlid);
}
