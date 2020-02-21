package com.lms.service;

import com.lms.dao.LearnRecordDao;
import com.lms.entity.LearnRecord;
import com.lms.vo.LearnVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LearnRecordService {

    @Resource
    private LearnRecordDao learnRecordDao;

    public LearnRecord findByUiid(Integer uiid){
        return learnRecordDao.findByUiid(uiid);
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
