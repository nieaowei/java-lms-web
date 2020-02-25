package com.lms.controller;

import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.utils.Result;
import com.lms.vo.LearnVO;
import com.lms.service.LearnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LearnRecordController {
    @Autowired
    LearnRecordService learnRecordService;

    @CrossOrigin
    @GetMapping(value = "/user/learnrecord")
    @RequiredToken
    public Result<List<LearnVO>> getLearnRecord(HttpServletRequest request) throws IOException {
        Long uiid = (long)request.getAttribute(AuthInterceptor.RESULT_KEY);
        List<LearnVO> learnVOList = learnRecordService.findLearnRecordByUiid(uiid);
        return new Result<List<LearnVO>>().setStatus(200).setMsg("获取成功").setData(learnVOList);
    }
}
