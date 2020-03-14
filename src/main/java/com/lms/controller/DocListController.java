package com.lms.controller;

import com.alibaba.fastjson.JSONObject;
import com.lms.entity.DocList;
import com.lms.interception.RequiredToken;
import com.lms.service.DocListService;
import com.lms.utils.Result;
import com.lms.vo.DocListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<List<DocList>> all() {
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
    public Result<List<DocListVO>> allPeron(HttpServletRequest request) {
        //无论查询到与否  docList对象都会生成，如果未查询到，其size为0
        List<DocListVO> docListList = docListService.findAllOrderAddFlag((long) request.getAttribute("uiid"));
        return new Result<List<DocListVO>>().setData(docListList).setStatus(200).setMsg("获取文档课程成功");
    }

    @CrossOrigin
    @GetMapping(value = "/doc/getpath")
    @RequiredToken
    public Result<String> getPath(HttpServletRequest request) {
        DocList docList;
        try {
            Integer dlid = Integer.parseInt(request.getParameter("dlid"));
            docList = docListService.findByDlid(dlid);
        } catch (Exception e) {
            return new Result<String>().setMsg("获取文档路径失败").setStatus(500);
        }
        if (docList != null) {
            return new Result<String>().setStatus(200).setData(docList.getPath()).setMsg("获取文档路径成功");
        }
        return new Result<String>().setMsg("获取文档路径失败").setStatus(500);

    }

    @CrossOrigin
    @GetMapping(value = "/admin/doc/all")
    public Result<List<DocListVO>> getAll(HttpServletRequest request) {
        List<DocListVO> docListVOList;
        try {
            docListVOList = docListService.findAllForAdmin();
        } catch (Exception e) {
            return new Result<List<DocListVO>>().setStatus(200).setMsg("获取文档列表失败");
        }
        return new Result<List<DocListVO>>().setStatus(200).setMsg("获取文档列表成功").setData(docListVOList);
    }

//    @CrossOrigin
//    @GetMapping(value = "/admin/doc/all")
//    public Result<List<DocListVO>> getAllPage(HttpServletRequest request) {
//        List<DocListVO> docListVOList;
//        try {
//            docListVOList = docListService.findAllForAdmin();
//        } catch (Exception e) {
//            return new Result<List<DocListVO>>().setStatus(200).setMsg("获取文档列表失败");
//        }
//        return new Result<List<DocListVO>>().setStatus(200).setMsg("获取文档列表成功").setData(docListVOList);
//    }

    @CrossOrigin
    @PostMapping(value = "/admin/doc/add")
    @RequiredToken
    public Result<DocListVO> addDoc(@RequestBody DocList docList, HttpServletRequest request) {
        DocListVO docListVO;
        try {
            Long uiid = (long) request.getAttribute("uiid");
            docList = docListService.saveAndRefresh(docList.setUiid(uiid));
            docListVO = new DocListVO(docList);
        } catch (Exception e) {
            return new Result<DocListVO>().setStatus(500).setMsg("新建文档课程失败");
        }
        return new Result<DocListVO>().setStatus(200).setMsg("新建文档课程成功").setData(docListVO);
    }

    @CrossOrigin
    @GetMapping("doc/search")
    @RequiredToken
    public Result<DocList> search(HttpServletRequest request) {
        String name = request.getParameter("name");
        DocList docList = docListService.findByName(name);
        return new Result<DocList>().setData(docList).setStatus(200).setMsg("查询文档");
    }

    @CrossOrigin
    @GetMapping(value = "/admin/doc/getone")
    public Result<DocListVO> getOne(HttpServletRequest request) {
        DocListVO docListVO;
        try {
            Integer dlid = Integer.parseInt(request.getParameter("dlid"));
            docListVO = docListService.findOneForAdmin(dlid);
        } catch (Exception e) {
            return new Result<DocListVO>().setStatus(500).setMsg("获取文档信息失败");
        }
        return new Result<DocListVO>().setStatus(200).setMsg("获取文档信息成功").setData(docListVO);
    }

    @CrossOrigin
    @GetMapping(value = "/admin/doc/deleteone")
    public Result<String> deleteOne(HttpServletRequest request) {
        DocListVO docListVO;
        try {
            Integer dlid = Integer.parseInt(request.getParameter("dlid"));
            if (docListService.deleteOneForAdmin(dlid)){
                return new Result<String>().setStatus(200).setMsg("删除课程成功");
            }
        } catch (Exception e) {
            return new Result<String>().setStatus(500).setMsg("删除课程失败");
        }
        return new Result<String>().setStatus(500).setMsg("删除课程失败");
    }

    @CrossOrigin
    @PostMapping(value = "/admin/doc/updateone")
    @RequiredToken
    public Result<DocListVO> updateOne(@RequestBody DocList docList,HttpServletRequest request) {
        DocListVO docListVO;
        try {
            docList.setUiid(docListService.findByDlid(docList.getDlid()).getUserEntity().getUiid());
            docList = docListService.save(docList);
            docListVO = new DocListVO(docList);
        } catch (Exception e) {
            return new Result<DocListVO>().setStatus(500).setMsg("更新课程失败");
        }
        return new Result<DocListVO>().setStatus(200).setMsg("更新课程成功").setData(docListVO);
    }

}
