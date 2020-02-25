package com.lms.controller;

import com.lms.entity.DocList;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.DocListService;
import com.lms.service.UserService;
import com.lms.utils.Result;
import com.lms.vo.LearnVO;
import com.lms.service.LearnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LearnRecordController {
    @Autowired
    private LearnRecordService learnRecordService;

    @Autowired
    private DocListService docListService;

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping(value = "/user/learnrecord")
    @RequiredToken
    public Result<List<LearnVO>> getLearnRecord(HttpServletRequest request) throws IOException {
        Long uiid = (long)request.getAttribute(AuthInterceptor.RESULT_KEY);
        List<LearnVO> learnVOList = learnRecordService.findLearnRecordByUiid(uiid);
        return new Result<List<LearnVO>>().setStatus(200).setMsg("获取成功").setData(learnVOList);
    }

    @CrossOrigin
    @PostMapping(value = "/user/addRecord")
    @RequiredToken
    public void addRecord(HttpServletRequest request){
        List<DocList> docListList = docListService.findByUserEntity(userService.findByUiid((Long) request.getAttribute(AuthInterceptor.RESULT_KEY)));
        for (DocList docList: docListList){
            LearnRecord learnRecord = new LearnRecord();
            learnRecord.setDocList(docList);
            learnRecordService.save(learnRecord);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/user/listRecord")
    @RequiredToken
    public Result list(@PageableDefault(size = 5, sort = {"lrid"}, direction = Sort.Direction.DESC)
                       Pageable pageable){
        Page<LearnRecord> pageableList = learnRecordService.findAll(pageable);
        return new Result<Page<LearnRecord>>().setData(pageableList).setStatus(500).setMsg("成功");
    }

}
