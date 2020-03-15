package com.lms.controller;

import com.lms.entity.DocList;
import com.lms.entity.TestInfo;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.TestInfoService;
import com.lms.service.UserService;
import com.lms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
}
