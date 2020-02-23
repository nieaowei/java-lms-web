package com.lms.service;

import com.lms.dao.LearnRecordDao;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.vo.LearnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LearnRecordService {

    @Autowired
    private LearnRecordDao learnRecordDao;

    public List<LearnRecord> findByUserEntity(UserEntity user){
        return learnRecordDao.findByUserEntity(user);
    }

    public Page<LearnRecord> findAll(Pageable pageable){
        return learnRecordDao.findAll(pageable);
    }

    public LearnRecord save(LearnRecord learnRecord){
        return learnRecordDao.save(learnRecord);
    }

    public List<LearnVO> findAllLearnVO(){
        return learnRecordDao.findAllLearnVO();
    }

    public LearnVO findLearnVoByName(String name){
        return learnRecordDao.findLearnVoByName(name);
    }
}
