package com.lms.controller;

import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.utils.ResultVOUtil;
import com.lms.vo.LearnVO;
import com.lms.vo.ResultVO;
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
    public ResultVO getLearnRecord(HttpServletRequest request) throws IOException {
        UserEntity userEntity = new UserEntity();
        userEntity.setUiid((Long) request.getAttribute(AuthInterceptor.RESULT_KEY));
        List<LearnVO> learnVOList = new ArrayList<>();
        learnVOList = learnRecordService.findAllLearnVO();
        return ResultVOUtil.success(learnVOList);
//        List<LearnRecord> data = learnRecordService.findByUserEntity(userEntity);
////        ResultVO result = new ResultVO();
////        result.setStatus(200);
////        result.setMsg("成功");
////        result.setData(data);
////        return result;
//        return new ResultVO<List<LearnRecord>>(200,"成功",data);
    }
}
