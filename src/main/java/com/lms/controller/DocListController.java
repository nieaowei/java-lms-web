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

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping(value = "/user/doclist")
    @RequiredToken
    public Result<List<DocList>> findDocList(HttpServletRequest request) throws IOException {
        Long uiid = (long)request.getAttribute(AuthInterceptor.RESULT_KEY);
        List<DocList> docListList = docListService.findByUserEntity(userService.findByUiid(uiid));
        return new Result<List<DocList>>().setStatus(200).setMsg("获取成功").setData(docListList);
    }

    @CrossOrigin
    @PostMapping(value = "/user/addDoclist")
    @RequiredToken
    public void addDoclist(HttpServletRequest request){
        List<DocList> docListList = docListService.findByUserEntity(userService.findByUiid((Long) request.getAttribute(AuthInterceptor.RESULT_KEY)));
        for (DocList docList: docListList){
            docListService.save(docList);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/user/listDoclist")
    @RequiredToken
    public Result list(@PageableDefault(size = 5, sort = {"dlid"}, direction = Sort.Direction.DESC)
                               Pageable pageable){
        Page<DocList> pageableList = docListService.findAll(pageable);
        return new Result<Page<DocList>>().setData(pageableList).setStatus(500).setMsg("成功");
    }
}
