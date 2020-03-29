package com.lms.service;

import com.lms.dao.DocListDao;
import com.lms.entity.DocList;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.entity.VideoList;
import com.lms.vo.DocListVO;
import com.lms.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DocListService {
    @Autowired
    private EntityManager entityManager;

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

    public DocList saveAndRefresh(DocList docList) {
        docList = docListDao.save(docList);
        entityManager.refresh(docList);
        return docList;
    }

    public Page<DocList> findAll(Pageable pageable) {
        return docListDao.findAll(pageable);
    }

    public List<DocList> findAll() {
        return docListDao.findAll();
    }

    /**
     * 查找所有文档并通过创建时间降序排序
     *
     * @return
     */
    public List<DocList> findAllOrder() {
        return docListDao.findAllByOrderByCreatetimeDesc();
    }

    /**
     * 返回文档列表，并针对用户添加是否已经添加到学习列表的标志
     *
     * @param uiid
     * @return
     */
    public List<DocListVO> findAllOrderAddFlag(Long uiid) {
        List<LearnRecord> learnRecords = learnRecordService.findByUiid(uiid);
        List<DocList> docListList = findAllOrder();
        List<DocListVO> docListVOList = new ArrayList<>();
        for (DocList docList : docListList) {
            DocListVO docListVO = new DocListVO().setUsername(docList.getUserEntity().getUsername())
                    .setName(docList.getName())
                    .setCreatetime(docList.getCreatetime())
                    .setUpdatetime(docList.getUpdatetime())
                    .setDlid(docList.getDlid())
                    .setDuration(docList.getDuration())
                    .setFlag(false)
                    .setCover(docList.getCover())
                    .setCount(docListDao.countByDlid(docList.getDlid()));
            for (LearnRecord learnRecord : learnRecords) {

                if (docList.getDlid().equals(learnRecord.getDocList().getDlid())) {
                    docListVO.setFlag(true);
                }
            }
            docListVOList.add(docListVO);
        }
        return docListVOList;
    }

    public List<DocList> findByUiid(Long uiid) {
        return docListDao.findByUserEntity_Uiid(uiid);
    }

    public DocList findByDlid(Integer dlid) {
        return docListDao.findByDlid(dlid);
    }

    public List<DocListVO> findAllForAdmin() {
        List<DocList> docListList = findAllOrder();
        List<DocListVO> docListVOList = new ArrayList<>();
        for (DocList docList : docListList) {
            DocListVO docListVO = new DocListVO().setUsername(docList.getUserEntity().getUsername())
                    .setName(docList.getName())
                    .setCreatetime(docList.getCreatetime())
                    .setUpdatetime(docList.getUpdatetime())
                    .setDlid(docList.getDlid())
                    .setDuration(docList.getDuration())
                    .setCover(docList.getCover())
                    .setPath(docList.getPath());
            docListVOList.add(docListVO);
        }
        return docListVOList;
    }

    public DocListVO findOneForAdmin(Integer dlid) {
        //查找学习用户
        List<LearnRecord> learnRecords = learnRecordService.findAllByDocList_Dlid(dlid);
        if (learnRecords.size() == 0) {
            DocList docList = docListDao.findByDlid(dlid);
            return new DocListVO(docList);
        }
        DocListVO docListVO = new DocListVO(learnRecords.get(0).getDocList());
        for (LearnRecord learnRecord : learnRecords) {
            UserVO userVO = new UserVO(learnRecord.getUserEntity());
            docListVO.getUsers().add(userVO.setDduration(learnRecord.getDuration()));
        }
        return docListVO;
    }

    public Boolean deleteOneForAdmin(Integer dlid){
        Integer res = docListDao.deleteDocListByDlid(dlid);
        return res != 0;
    }

    public DocList updateOne(DocList docList){
        DocList temp = docListDao.findByDlid(docList.getDlid());
        temp.setPath(docList.getPath()).setDuration(docList.getDuration())
                .setCover(docList.getCover()).setName(docList.getName());
        temp = docListDao.saveAndFlush(temp);
        entityManager.refresh(temp);
        return temp;
    }

}
