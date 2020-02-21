package com.lms.dao;

import com.lms.entity.DocList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocListDao extends JpaRepository<DocList, Integer> {
    DocList findByName(String name);
    Page<DocList> findAll(Pageable pageable);
    DocList save(DocList docList);
}
