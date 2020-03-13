package com.lms.service;

import com.lms.dao.DocListDao;
import com.lms.dao.LearnRecordDao;
import com.lms.dao.VideoLearnRecordDao;
import com.lms.dao.VideoListDao;
import com.lms.entity.*;
import com.lms.vo.DocListVO;
import com.lms.vo.LearnVO;
import com.lms.vo.VideoLearnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VideoLearnRecordService {
    @Autowired
    private VideoLearnRecordDao videoLearnRecordDao;

    @Autowired
    private EntityManager entityManager;

    public VideoLearnRecord findByLrid(Integer lrid){
        return videoLearnRecordDao.findByLrid(lrid);
    }

    public VideoLearnRecord findByLridAndUiid(Integer lrid, Long uiid){
        return videoLearnRecordDao.findByLridAndUserEntity(lrid, new UserEntity().setUiid(uiid));
    }

    public VideoLearnRecord findByVlidAndUiid(Integer vlid, Long uiid){
        return videoLearnRecordDao.findByVideoListAndUserEntity(new VideoList().setVlid(vlid),new UserEntity().setUiid(uiid));
    }

    public List<VideoLearnRecord> findByUiid(Long uiid){
        return videoLearnRecordDao.findByUserEntity(new UserEntity().setUiid(uiid));
    }

    public Page<VideoLearnRecord> findAll(Pageable pageable){
        return videoLearnRecordDao.findAll(pageable);
    }

    public VideoLearnRecord save(VideoLearnRecord videoLearnRecord){
        return videoLearnRecordDao.save(videoLearnRecord);
    }

    public List<VideoLearnVO> findLearnRecordByUiid(Long uiid){
        List<VideoLearnRecord> videoLearnRecords = videoLearnRecordDao.findByUserEntity(new UserEntity().setUiid(uiid));
        List<VideoLearnVO> videoLearnVOList = new ArrayList<>();
        for (VideoLearnRecord videoLearnRecord : videoLearnRecords) {
            VideoLearnVO videoLearnVO = new VideoLearnVO().setName(videoLearnRecord.getVideoList().getName())
                    .setVlid(videoLearnRecord.getVideoList().getVlid())
                    .setDoc_duration(videoLearnRecord.getVideoList().getDuration())
                    .setDuration(videoLearnRecord.getDuration())
                    .isFinished()
                    .computedPercent()
                    .setPath(videoLearnRecord.getVideoList().getPath())
                    .setCover(videoLearnRecord.getVideoList().getCover());
//            videoLearnVOMap.put(videoLearnVO.getVlid(),videoLearnVO);
            videoLearnVOList.add(videoLearnVO);
        }
        return videoLearnVOList;
    }

    public VideoLearnRecord saveAndRefresh(VideoLearnRecord learnRecord){
        learnRecord = videoLearnRecordDao.save(learnRecord);
        entityManager.refresh(learnRecord);
        return learnRecord;
    }

}
