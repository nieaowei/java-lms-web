package com.lms.controller;

import com.alibaba.fastjson.JSONObject;
import com.lms.entity.DocList;
import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.DocListService;
import com.lms.service.UserService;
import com.lms.utils.Result;
import com.lms.vo.DocListVO;
import com.lms.vo.LearnVO;
import com.lms.service.LearnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LearnRecordController {
    @Autowired
    private LearnRecordService learnRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private DocListService docListService;

    /**
     * 查询文档学习的记录列表
     *
     * @param request 用于解析拦截器传来的已验证的token中的用户账号
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/user/doc/record")
    @RequiredToken
    public Result<List<LearnVO>> getLearnRecord(HttpServletRequest request) {
        Long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
        List<LearnVO> learnVOList = learnRecordService.findLearnRecordByUiid(uiid);//无论是否查询到都会生成对象，无需判错
        return new Result<List<LearnVO>>().setStatus(200).setMsg("获取学习记录成功").setData(learnVOList);
    }

    /**
     * 增加学习文档到个人学习记录
     *
     * @param request 用于解析拦截器传来的已验证的token中的用户账号
     */
    @CrossOrigin
    @PostMapping(value = "/user/doc/addRecord")
    @RequiredToken
    public Result<List<DocListVO>> addRecord(@RequestBody JSONObject jsonParam,HttpServletRequest request) {
        List<DocListVO> docListList;
        try {
            Integer dlid = jsonParam.getInteger("dlid");
            LearnRecord learnRecord = new LearnRecord().setDlid(dlid)
                    .setUiid((long) request.getAttribute(AuthInterceptor.RESULT_KEY)).setDuration(0);
            learnRecordService.save(learnRecord);
            docListList = docListService.findAllOrderAddFlag((long)request.getAttribute("uiid"));
        } catch (Exception e) {
            if (e.getMessage().contains("dlid")) {//捕获到重复键值
                return new Result<List<DocListVO>>().setStatus(500).setMsg("已经添加该课程");
            }
            return new Result<List<DocListVO>>().setStatus(500).setMsg("添加课程失败");
        }
        return new Result<List<DocListVO>>().setStatus(200).setMsg("添加课程成功").setData(docListList);
    }

    /**
     * 更新用户文档的学习时间
     * @param request 用于解析拦截器传来的已验证的token中的用户账号，以及相关参数
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/user/doc/update")
    @RequiredToken
    public Result<LearnRecord> updateDuration(@RequestBody JSONObject jsonParam,HttpServletRequest request) {
        LearnRecord learnRecord;
        try {
            Integer dlid = jsonParam.getInteger("dlid");
            Integer duration = jsonParam.getInteger("newduration");
            /**
             * 为什么要通过uiid和dlid去查询更新呢？
             * 为了防止uiid操作了不属于自己的lrid。
             */
            learnRecord = learnRecordService.findByDlidAndUiid(dlid,(Long) request.getAttribute(AuthInterceptor.RESULT_KEY));
            learnRecord = learnRecordService.save(learnRecord.setDuration(duration));//设置新的时间并更新
        } catch (Exception e) {
            return new Result<LearnRecord>().setStatus(500).setMsg("更新时间失败");
        }
        if (learnRecord!=null){
            return new Result<LearnRecord>().setStatus(200).setMsg("更新时间成功").setData(learnRecord);
        }
        return new Result<LearnRecord>().setStatus(500).setMsg("更新时间失败");
    }


}
