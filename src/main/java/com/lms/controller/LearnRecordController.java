package com.lms.controller;

import com.lms.entity.LearnRecord;
import com.lms.entity.UserEntity;
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

@Controller
public class LearnRecordController {
    @Autowired
    UserService userService;
    LearnRecordService learnRecordService;

    @CrossOrigin
    @GetMapping(value = "/user/learnrecord")
    @ResponseBody
    public Result getLearnRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //解析token中的uiid
        Long uiid = -1L;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equalsIgnoreCase("token")){
                uiid = TokenUtil.validateToken(cookie.getValue());
            }
        }

        //判断uiid是否有效（token是否有效）
        if (null == userService.getByUiid(uiid)){
            //token无效，跳转登录页面
            response.sendRedirect("/user/login");
            return new Result(201,"token校验失败，请重新登录");
        }else{
            //token有效，返回课程数据
            List<LearnRecord> data = null;
            data = learnRecordService.findByUserEntity(userService.getByUiid(uiid));
            return new Result<List<LearnRecord>>(200,"查询成功",data);
        }
    }
}
