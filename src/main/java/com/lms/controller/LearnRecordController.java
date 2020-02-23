package com.lms.controller;

import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.result.Result;
import com.lms.service.LearnRecordService;
import com.lms.service.UserService;
import com.lms.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredToken
public class LearnRecordController {
    @Autowired
    LearnRecordService learnRecordService;

    @CrossOrigin
    @GetMapping(value = "/user/learnrecord")
    public Result getLearnRecord(HttpServletRequest request) throws IOException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUiid((Long) request.getAttribute(AuthInterceptor.RESULT_KEY));
        List<LearnRecord> data = learnRecordService.findByUserEntity(userEntity);
        return new Result<List<LearnRecord>>(200,"成功",data);
    }
}
