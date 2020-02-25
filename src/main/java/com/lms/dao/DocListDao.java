package com.lms.dao;

import com.lms.entity.DocList;
import com.lms.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocListDao extends JpaRepository<DocList, Integer> {
    DocList findByName(String name);
    DocList findByDlid(Long dlid);
    Page<DocList> findAll(Pageable pageable);
    List<DocList> findByUserEntity(UserEntity uiid);
}
