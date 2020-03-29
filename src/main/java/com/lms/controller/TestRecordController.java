package com.lms.controller;

import com.alibaba.fastjson.JSONObject;
import com.lms.entity.TestContent;
import com.lms.entity.TestInfo;
import com.lms.entity.TestRecord;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.TestContentService;
import com.lms.service.TestInfoService;
import com.lms.service.TestRecordService;
import com.lms.service.UserService;
import com.lms.utils.Result;
import com.lms.vo.TestInfoVO;
import com.lms.vo.TestRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping(value = "/user/test/record")
    @RequiredToken
    public Result<List<TestRecordVO>> getLearnRecord(HttpServletRequest request) {
        Long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
        List<TestRecordVO> learnVOList = testRecordService.findVOByUiid(uiid);//无论是否查询到都会生成对象，无需判错
        return new Result< List<TestRecordVO>>().setStatus(200).setMsg("获取学习记录成功").setData(learnVOList);
    }

    @CrossOrigin
    @PostMapping(value = "/user/test/postone")
    @RequiredToken
    public Result<TestRecordVO> postOne(@RequestBody TestInfoVO testInfoVO ,HttpServletRequest request) {
        //无论查询到与否  docList对象都会生成，如果未查询到，其size为0
//        List<TestInfoVO> testInfoVOS = testInfoService.findAllOrderAddFlag((long) request.getAttribute("uiid"));
        TestRecordVO testRecordVO;
        try {
            Long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
            TestRecord check = testRecordService.findByUiidAndTiid(uiid,testInfoVO.getTiid());

            TestRecord testRecord = testContentService.answer(testInfoVO.getTopics());
            testRecord.setUserEntity(new UserEntity().setUiid(uiid));
            testRecord.setTestInfo(new TestInfo().setTiid(testInfoVO.getTiid()));
            if (null == check){
                testRecord = testRecordService.saveAndRefresh(testRecord);
            }else{
                if (check.getCount()<=0){
                    return new Result<TestRecordVO>().setStatus(400).setMsg("您已没有考试次数");
                }
                check.setCount(check.getCount()-1);
                check.setGrade(testRecord.getGrade());
                testRecord = testRecordService.saveAndFlush(check);
            }
            testRecordVO=new TestRecordVO();
            BeanUtils.copyProperties(testRecord,testRecordVO);
            BeanUtils.copyProperties(testRecord.getTestInfo(),testRecordVO);
            testRecordVO.setUpdatetime(testRecord.getUpdatetime());
            testRecordVO.setPersoncount(testRecord.getCount());
        }catch (Exception e){
            return new Result<TestRecordVO>().setStatus(400).setMsg("提交试卷失败");
        }
        return new Result<TestRecordVO>().setData(testRecordVO).setStatus(200).setMsg("提交试卷成功");
    }

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
