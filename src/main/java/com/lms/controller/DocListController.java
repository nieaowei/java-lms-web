package com.lms.controller;

import com.lms.entity.DocList;
import com.lms.interception.RequiredToken;
import com.lms.service.DocListService;
import com.lms.utils.Result;
import com.lms.vo.DocListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @CrossOrigin
    @GetMapping(value = "/doc/getpath")
    @RequiredToken
    public Result<String> getPath(HttpServletRequest request){
        DocList docList;
        try{
            Integer dlid = Integer.parseInt(request.getParameter("dlid"));
            docList = docListService.findByDlid(dlid);
        }catch (Exception e){
            return new Result<String>().setMsg("获取文档路径失败").setStatus(500);
        }
        if (docList!=null){
            return new Result<String>().setStatus(200).setData(docList.getPath()).setMsg("获取文档路径成功");
        }
        return new Result<String>().setMsg("获取文档路径失败").setStatus(500);

    }

    @CrossOrigin
    @GetMapping("doc/search")
    @RequiredToken
    public Result<DocList> search(HttpServletRequest request){
        String name = request.getParameter("name");
        DocList docList = docListService.findByName(name);
        return new Result<DocList>().setData(docList).setStatus(200).setMsg("查询文档");
    }

    @CrossOrigin
    @PostMapping("doc/add")
    @RequiredToken
    public Result<DocList> add(HttpServletRequest request){
        String cover = request.getParameter("cover");
        String path = request.getParameter("path");
        String name = request.getParameter("name");
        Integer duration = Integer.valueOf(request.getParameter("duration"));
        DocList docList = new DocList();
        docList.setCover(cover).setPath(path).setName(name).setDuration(duration);
        docList = docListService.save(docList);
        return new Result<DocList>().setData(docList).setStatus(200).setMsg("新增文档");
    }
    
}
