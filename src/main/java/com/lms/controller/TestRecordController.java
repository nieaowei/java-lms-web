package com.lms.controller;

import com.alibaba.fastjson.JSONObject;
import com.lms.entity.TestContent;
import com.lms.entity.TestInfo;
import com.lms.entity.TestRecord;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.service.TestContentService;
import com.lms.service.TestInfoService;
import com.lms.service.TestRecordService;
import com.lms.service.UserService;
import com.lms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLScriptElement;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class TestRecordController {

    @Autowired
    private TestRecordService testRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestInfoService testInfoService;

    @Autowired
    private TestContentService testContentService;

    @CrossOrigin
    @GetMapping(value = "testrecord/all")
    public Result<List<TestRecord>> findAll(HttpServletRequest request){
        long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
        UserEntity userEntity = userService.findByUiid(uiid);
        List<TestRecord> testRecordList = testRecordService.findAllByUserEntity(userEntity);
        return new Result<List<TestRecord>>().setData(testRecordList).setStatus(200).setMsg("查询当前用户所有试卷成绩");
    }

    @CrossOrigin
    @GetMapping(value = "testrecord/one")
    public Result<TestRecord> findOne(HttpServletRequest request){
        long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
        UserEntity userEntity = userService.findByUiid(uiid);
        Integer tiid = (Integer) request.getAttribute("tiid");
        TestInfo testInfo = testInfoService.findByTiid(tiid);
        TestRecord testRecord = testRecordService.findByUserEntityAndTestInfo(userEntity, testInfo);
        return new Result<TestRecord>().setData(testRecord).setStatus(200).setMsg("查询单个成绩");
    }

    @CrossOrigin
    @PostMapping(value = "testrecord/calculate")
    public void calculate(HttpServletRequest request,
                          @RequestBody JSONObject jsonParam){
        Integer tiid = (Integer) request.getAttribute("tiid");
        long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
        TestInfo testInfo = testInfoService.findByTiid(tiid);
        List<TestContent> testContentList = testContentService.findAllByTestInfo(testInfo);
//        Integer size = testContentService.countAllByTcid();
        Integer grade = 0;
//        for (int i = 1; i <= size; i++){
//            if (jsonParam.getInteger(String.valueOf(i)).equals(testContentList.get(i).getAnswer())){
//                grade += testContentList.get(i).getScore();
//            }
//        }
        TestRecord testRecord = new TestRecord();
        testRecord.setUserEntity(userService.findByUiid(uiid));
        testRecord.setTestInfo(testInfoService.findByTiid(tiid));
        testRecord.setGrade(grade);
    }
}
