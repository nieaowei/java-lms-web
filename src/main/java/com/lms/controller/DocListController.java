package com.lms.controller;

import com.lms.entity.DocList;
import com.lms.entity.LearnRecord;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.DocListService;
import com.lms.service.LearnRecordService;
import com.lms.service.UserService;
import com.lms.utils.Result;
import com.lms.vo.LearnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class DocListController {
    @Autowired
    private DocListService docListService;

    @CrossOrigin
    @GetMapping(value = "/doc/all")
    @RequiredToken
    public Result<List<DocList>> all(){
        List<DocList> docListList = docListService.findAllOrder();
        return new Result<List<DocList>>().setData(docListList).setStatus(200).setMsg("获取文档课程成功");
    }
}
