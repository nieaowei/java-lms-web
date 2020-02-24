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
import java.util.ArrayList;
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

    public List<LearnVO> findLearnRecordByUiid(Long uiid){
        List<LearnRecord> learnRecords = learnRecordDao.findByUserEntity(new UserEntity().setUiid(uiid));
        List<LearnVO> learnVOList=new ArrayList<>();
        for (LearnRecord learnrecord : learnRecords) {
            LearnVO learnVO = new LearnVO().setName(learnrecord.getDocList().getName())
                    .setDlid(learnrecord.getDocList().getDlid())
                    .setDoc_duration(learnrecord.getDocList().getDuration())
                    .setDuration(learnrecord.getDuration());
            learnVOList.add(learnVO);
        }
        return learnVOList;
    }

////    public List<LearnVO> findAllLearnVO(Long uiid){
////        return learnRecordDao.findAllLearnVO(uiid);
////    }
//
//    public LearnVO findLearnVoByName(String name){
//        return learnRecordDao.findLearnVoByName(name);
//    }
}
