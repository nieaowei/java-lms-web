package com.lms.controller;

import com.lms.entity.TestContent;
import com.lms.entity.TestInfo;
import com.lms.service.TestContentService;
import com.lms.service.TestInfoService;
import com.lms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TestContentController {

    @Autowired
    private TestContentService testContentService;

    @Autowired
    private TestInfoService testInfoService;

    @CrossOrigin
    @GetMapping("testcontent/all")
    public Result<List<TestContent>> all(HttpServletRequest request){
        Integer tiid = (Integer) request.getAttribute("tiid");
        List<TestContent> testContentList = testContentService.findAllByTestInfo(testInfoService.findByTiid(tiid));
        return new Result<List<TestContent>>().setData(testContentList).setStatus(200).setMsg("查询所有题目");
    }

    /**
     * 逐个添加题目
     * @param
     * @return
     */
    @CrossOrigin
    @PostMapping("testcontent/add")
    public void save(HttpServletRequest request){
        Integer tiid = (Integer) request.getAttribute("tiid");
        TestInfo testInfo = testInfoService.findByTiid(tiid);
        if (testInfo == null){
           return;
        }
        TestContent testContent = (TestContent) request.getAttribute("tcid");
        testContentService.save(testContent);
    }
}
