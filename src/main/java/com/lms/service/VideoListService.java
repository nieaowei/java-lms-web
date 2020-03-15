package com.lms.service;

import com.lms.dao.VideoListDao;
import com.lms.entity.*;
import com.lms.vo.VideoListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoListService {
    @Resource
    private VideoListDao videoListDao;

    @Autowired
    public EntityManager entityManager;

    @Autowired
    private VideoLearnRecordService videoLearnRecordService;

    public VideoList findByName(String name) {
        return videoListDao.findByName(name);
    }

    public VideoList save(VideoList videoList) {
        return videoListDao.save(videoList);
    }

    public Page<VideoList> findAll(Pageable pageable) {
        return videoListDao.findAll(pageable);
    }

    public List<VideoList> findAll() {
        return videoListDao.findAll();
    }

    /**
     * 查找所有文档并通过创建时间降序排序
     * @return
     */
    public List<VideoList> findAllOrderByCreatetimeDesc(){
        return videoListDao.findAllByOrderByCreatetimeDesc();
    }

    /**
     * 返回文档列表，并针对用户添加是否已经添加到学习列表的标志
     * @param uiid
     * @return
     */
    public List<VideoListVO> findAllOrderAddFlag(Long uiid){
        List<VideoLearnRecord> learnRecords = videoLearnRecordService.findByUiid(uiid);
        List<VideoList> videoLists = findAllOrderByCreatetimeDesc();
        List<VideoListVO> videoListVOMap = new ArrayList<>();
        for (VideoList videoList: videoLists) {
            VideoListVO videoListVO = new VideoListVO().setUsername(videoList.getUserEntity().getUsername())
                    .setName(videoList.getName())
                    .setCreatetime(videoList.getCreatetime())
                    .setUpdatetime(videoList.getUpdatetime())
                    .setVlid(videoList.getVlid())
                    .setDuration(videoList.getDuration())
                    .setFlag(false)
                    .setCover(videoList.getCover());
            for (VideoLearnRecord learnRecord: learnRecords) {

                if (videoList.getVlid().equals(learnRecord.getVideoList().getVlid())){
                    videoListVO.setFlag(true);
                }
            }
            videoListVOMap.add(videoListVO);
        }
        return videoListVOMap;
    }

    public List<VideoList> findByUserEntity(UserEntity user) {
        return videoListDao.findByUserEntity(user);
    }

    public VideoList findByVlid(Integer vlid) {
        return videoListDao.findByVlid(vlid);
    }

    public void delete(Integer vlid){ videoListDao.deleteById(vlid);}

    public VideoList saveAndRefresh(VideoList videoList){
        videoList = videoListDao.save(videoList);
        entityManager.refresh(videoList);
        return videoList;
    }

}
