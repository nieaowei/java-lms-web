package com.lms.service;

import com.lms.dao.DocListDao;
import com.lms.entity.DocList;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.vo.DocListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocListService {

    @Resource
    private DocListDao docListDao;
    @Autowired
    private LearnRecordService learnRecordService;

    public DocList findByName(String name) {
        return docListDao.findByName(name);
    }

    public DocList save(DocList docList) {
        return docListDao.save(docList);
    }

    public Page<DocList> findAll(Pageable pageable) {
        return docListDao.findAll(pageable);
    }

    public List<DocList> findAll() {
        return docListDao.findAll();
    }

    /**
     * 查找所有文档并通过创建时间降序排序
     * @return
     */
    public List<DocList> findAllOrder(){
        return docListDao.findAllByOrderByCreatetimeDesc();
    }

    /**
     * 返回文档列表，并针对用户添加是否已经添加到学习列表的标志
     * @param uiid
     * @return
     */
    public List<DocListVO> findAllOrderAddFlag(Long uiid){
        List<LearnRecord> learnRecords = learnRecordService.findByUiid(uiid);
        List<DocList> docListList = findAllOrder();
        List<DocListVO> docListVOList = new ArrayList<>();
        for (DocList docList:docListList) {
            DocListVO docListVO = new DocListVO().setUsername(docList.getUserEntity().getUsername())
                    .setName(docList.getName())
                    .setCreatetime(docList.getCreatetime())
                    .setUpdatetime(docList.getUpdatetime())
                    .setDlid(docList.getDlid())
                    .setDuration(docList.getDuration())
                    .setFlag(false)
                    .setCover(docList.getCover());
            for (LearnRecord learnRecord:learnRecords) {

                if (docList.getDlid().equals(learnRecord.getDocList().getDlid())){
                    docListVO.setFlag(true);
                }
            }
            docListVOList.add(docListVO);
        }
        return docListVOList;
    }

    public List<DocList> findByUserEntity(UserEntity user) {
        return docListDao.findByUserEntity(user);
    }

    public DocList findByDlid(Integer dlid) {
        return docListDao.findByDlid(dlid);
    }
}
