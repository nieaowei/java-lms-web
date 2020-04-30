package com.lms.service;

import com.lms.dao.LearnRecordDao;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.vo.LearnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LearnRecordService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private LearnRecordDao learnRecordDao;

    public LearnRecord findByLrid(Integer lrid) {
        return learnRecordDao.findByLrid(lrid);
    }

    public List<LearnRecord> findAllByDocList_Dlid(Integer dlid){
        return learnRecordDao.findAllByDocList_Dlid(dlid);
    }


    public LearnRecord findByDlidAndUiid(Integer dlid, Long uiid) {
        return learnRecordDao.findByDocList_DlidAndUserEntity_Uiid(dlid, uiid);
    }

    public List<LearnRecord> findByUiid(Long uiid) {
        return learnRecordDao.findByUserEntity_UiidOrderByCreatetimeDesc(uiid);
    }


    public Page<LearnRecord> findAll(Pageable pageable) {
        return learnRecordDao.findAll(pageable);
    }

    public LearnRecord save(LearnRecord learnRecord) {
        return learnRecordDao.save(learnRecord);
    }

    public LearnRecord saveAndRefresh(LearnRecord learnRecord){
        learnRecord = learnRecordDao.save(learnRecord);
        entityManager.refresh(learnRecord);
        return learnRecord;
    }

    public  List<LearnVO> findLearnRecordVOByUiid(Long uiid) {
//        Map<Integer, LearnVO> maps= new HashMap<>();
        List<LearnVO> learnVOList = new ArrayList<>();
        List<LearnRecord> learnRecords = learnRecordDao.findByUserEntity_UiidOrderByCreatetimeDesc(uiid);
        for (LearnRecord learnrecord : learnRecords) {
            LearnVO learnVO = new LearnVO().setName(learnrecord.getDocList().getName())
                    .setDlid(learnrecord.getDocList().getDlid())
                    .setDoc_duration(learnrecord.getDocList().getDuration())
                    .setDuration(learnrecord.getDuration())
                    .isFinished()
                    .computedPercent()
                    .setPath(learnrecord.getDocList().getPath())
                    .setCover(learnrecord.getDocList().getCover());
//            maps.put(learnrecord.getDocList().getDlid(),learnVO);
            learnVOList.add(learnVO);
        }
        return learnVOList;
    }

    public LearnRecord add(Long uiid,Integer dlid){
        return learnRecordDao.insert(uiid,dlid);
    }

    public void delete(Integer lrid){
        learnRecordDao.deleteById(lrid);
    }

    public Boolean deleteByUiidAndDlid(Long uiid,Integer dlid){
        return learnRecordDao.deleteByDocList_DlidAndUserEntity_Uiid(dlid,uiid)!=0;
    }
}
