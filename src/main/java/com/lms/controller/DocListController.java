package com.lms.controller;

import com.lms.entity.DocList;
import com.lms.interception.RequiredToken;
import com.lms.service.DocListService;
import com.lms.utils.Result;
import com.lms.vo.DocListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DocListController {
    @Autowired
    private DocListService docListService;

    /***
     * 查询所有课程，未针对用户进行个性化处理
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/doc/all")
    public Result<List<DocList>> all(){
        List<DocList> docListList = docListService.findAllOrder();
        return new Result<List<DocList>>().setData(docListList).setStatus(200).setMsg("获取文档课程成功");
    }

    /***
     * 针对用户进行个性化数据处理，添加学习标志
     * @param request
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/doc/all/person")
    @RequiredToken
    public Result<List<DocListVO>> allPeron(HttpServletRequest request){
        //无论查询到与否  docList对象都会生成，如果未查询到，其size为0
        List<DocListVO> docListList = docListService.findAllOrderAddFlag((long)request.getAttribute("uiid"));
        return new Result<List<DocListVO>>().setData(docListList).setStatus(200).setMsg("获取文档课程成功");
    }
}
