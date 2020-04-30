package com.lms.controller;

import com.lms.entity.DocList;
import com.lms.entity.TestContent;
import com.lms.entity.TestInfo;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredAdmin;
import com.lms.interception.RequiredToken;
import com.lms.service.TestContentService;
import com.lms.service.TestInfoService;
import com.lms.service.UserService;
import com.lms.utils.Result;
import com.lms.vo.DocListVO;
import com.lms.vo.TestInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TestInfoController {

    @Autowired
    private TestInfoService testInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestContentService testContentService;

    /***
     * 查询所有试卷，未针对用户进行个性化处理
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/testinfo/all")
    public Result<List<TestInfo>> all(){
        List<TestInfo> testInfoList = testInfoService.findOrder();
        return new Result<List<TestInfo>>().setData(testInfoList).setStatus(200).setMsg("获取试卷成功");
    }

    /**
     * 创建一个试题，等待出题
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "testinfo/create")
    public Result<TestInfo> create(HttpServletRequest request){
        long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
        String title = (String) request.getAttribute("title");
        TestInfo testInfo = new TestInfo();
        testInfo.setUserEntity(userService.findByUiid(uiid));
        testInfo.setTitle(title);
        testInfoService.save(testInfo);
        return new Result<TestInfo>().setData(testInfo).setStatus(200).setMsg("创建试题");
    }

    @CrossOrigin
    @PostMapping("testinfo/delete")
    @RequiredToken
    public void delete(HttpServletRequest request){
        Integer tiid = Integer.valueOf(request.getParameter("tiid"));
        testInfoService.delete(tiid);
    }

    @CrossOrigin
    @GetMapping(value = "/test/all/person")
    @RequiredToken
    public Result<List<TestInfoVO>> allPeron(HttpServletRequest request) {
        //无论查询到与否  docList对象都会生成，如果未查询到，其size为0
        List<TestInfoVO> testInfoVOS = testInfoService.findAllOrderAddFlag((long) request.getAttribute("uiid"));
        return new Result<List<TestInfoVO>>().setData(testInfoVOS).setStatus(200).setMsg("获取试卷成功");
    }

    @CrossOrigin
    @GetMapping(value = "/test/getone")
//    @RequiredToken
    public Result<TestInfoVO> getOne(HttpServletRequest request) {
        //无论查询到与否  docList对象都会生成，如果未查询到，其size为0
//        List<TestInfoVO> testInfoVOS = testInfoService.findAllOrderAddFlag((long) request.getAttribute("uiid"));
        Integer tiid = Integer.parseInt(request.getParameter("tiid"));
        TestInfoVO testInfoVO = testInfoService.getTestInfoVOAll(tiid);
        return new Result<TestInfoVO>().setData(testInfoVO).setStatus(200).setMsg("获取试卷成功");
    }

    @CrossOrigin
    @GetMapping(value = "/admin/test/all")
    @RequiredAdmin
    public Result<List<TestInfoVO>> getAll(HttpServletRequest request) {
        List<TestInfoVO> testInfoVOS;
        try {
            testInfoVOS = testInfoService.findAllForAdmin();
        } catch (Exception e) {
            return new Result<List<TestInfoVO>>().setStatus(200).setMsg("获取文档列表失败");
        }
        return new Result<List<TestInfoVO>>().setStatus(200).setMsg("获取文档列表成功").setData(testInfoVOS);
    }

    @CrossOrigin
    @PostMapping(value = "/admin/test/add")
    @RequiredAdmin
    public Result<TestInfoVO> addTest(@RequestBody TestInfoVO testInfoVO, HttpServletRequest request) {
        TestInfoVO docListVO = new TestInfoVO();
        try {
            Long uiid = (long) request.getAttribute("uiid");
            TestInfo testInfo = new TestInfo();
            BeanUtils.copyProperties(testInfoVO,testInfo);
            testInfo.setUserEntity(new UserEntity().setUiid(uiid));
            //设置试题数量
            testInfo.setNum(testInfoVO.getTopics().size());
            Integer sum=0;
            //计算总分
            for (TestContent testContent:testInfoVO.getTopics()) {
                testContent.setScore(10);
                sum+=testContent.getScore();
            }
            testInfo.setSum(sum);
            testInfo = testInfoService.saveAndRefresh(testInfo);
            for (TestContent testContent:testInfoVO.getTopics()){
                testContent.setTestInfo(testInfo);
            }
            testContentService.saveAll(testInfoVO.getTopics());
            BeanUtils.copyProperties(testInfo.getUserEntity(),testInfoVO);
            BeanUtils.copyProperties(testInfo,testInfoVO);
        } catch (Exception e) {
            return new Result<TestInfoVO>().setStatus(500).setMsg("新建试卷失败");
        }
        return new Result<TestInfoVO>().setStatus(200).setMsg("新建试卷成功").setData(testInfoVO);
    }
    @CrossOrigin
    @GetMapping(value = "/admin/test/getone")
    @RequiredAdmin

    public Result<TestInfoVO> getone(HttpServletRequest request) {
        TestInfoVO testInfoVO;
        try {
            Integer tiid = Integer.parseInt(request.getParameter("tiid"));
            testInfoVO = testInfoService.findOneForAdmin(tiid);
        } catch (Exception e) {
            return new Result<TestInfoVO>().setStatus(500).setMsg("获取试卷信息失败");
        }
        return new Result<TestInfoVO>().setStatus(200).setMsg("获取试卷信息成功").setData(testInfoVO);
    }

    @CrossOrigin
    @GetMapping(value = "/admin/test/deleteone")
    @RequiredAdmin

    public Result<String> deleteOne(HttpServletRequest request) {
        try {
            Integer tiid = Integer.parseInt(request.getParameter("tiid"));
            if (testInfoService.deleteOneForAdmin(tiid)){
                return new Result<String>().setStatus(200).setMsg("删除课程成功");
            }
        } catch (Exception e) {
            return new Result<String>().setStatus(500).setMsg("删除课程失败");
        }
        return new Result<String>().setStatus(500).setMsg("删除课程失败");
    }

    @CrossOrigin
    @PostMapping(value = "/admin/test/updateone")
    @RequiredAdmin

    public Result<TestInfoVO> updateOne(@RequestBody TestInfoVO testInfoVO,HttpServletRequest request) {
        TestInfoVO docListVO = new TestInfoVO();
        try {
            Long uiid = (long) request.getAttribute("uiid");
            TestInfo testInfo = new TestInfo();
            BeanUtils.copyProperties(testInfoVO,testInfo);
            testInfo.setUserEntity(new UserEntity().setUiid(uiid));
            //设置试题数量
            testInfo.setNum(testInfoVO.getTopics().size());
            Integer sum=0;
            //计算总分
            for (TestContent testContent:testInfoVO.getTopics()) {
                sum+=testContent.getScore();
            }
            testInfo.setSum(sum);
            testInfo = testInfoService.saveAndRefresh(testInfo);
            for (TestContent testContent:testInfoVO.getTopics()){
                testContent.setTestInfo(testInfo);
            }
            testContentService.saveAll(testInfoVO.getTopics());
            BeanUtils.copyProperties(testInfo.getUserEntity(),testInfoVO);
            BeanUtils.copyProperties(testInfo,testInfoVO);
        } catch (Exception e) {
            return new Result<TestInfoVO>().setStatus(500).setMsg("修改试卷失败");
        }
        return new Result<TestInfoVO>().setStatus(200).setMsg("修改试卷成功").setData(testInfoVO);
    }



}
