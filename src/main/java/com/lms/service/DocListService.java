package com.lms.service;

import com.lms.dao.DocListDao;
import com.lms.entity.DocList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DocListService {

    @Resource
    private DocListDao docListDao;

    public DocList findByName(String name){
        return docListDao.findByName(name);
    }

    public DocList save(DocList docList){
        return docListDao.save(docList);
    }

    public Page<DocList> findAll(Pageable pageable){
        return docListDao.findAll(pageable);
    }
}