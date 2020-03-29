package com.lms.dao;

import com.lms.entity.DocList;
import com.lms.vo.DocListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocListDao extends JpaRepository<DocList, Integer> {
    DocList findByName(String name);
    DocList findByDlid(Integer dlid);
    Page<DocList> findAll(Pageable pageable);
    List<DocList> findByUserEntity_Uiid(Long userEntity_uiid);
    List<DocList> findAllByOrderByCreatetimeDesc();
    List<DocListDTO> findAllByOrderByCreatetimeAsc();
    Integer deleteDocListByDlid(Integer dlid);
    Integer countByDlid(Integer dlid);
}
