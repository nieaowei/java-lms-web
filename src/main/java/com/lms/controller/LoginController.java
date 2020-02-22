package com.lms.controller;

import com.lms.entity.UserEntity;
import com.lms.result.Result;
import com.lms.service.UserService;
import com.lms.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/login")
    @ResponseBody
    public Result<String> login(@RequestBody UserEntity requestUser, HttpServletResponse response){
        String username = requestUser.getUsername();
        UserEntity user = userService.get(username,requestUser.getPassword());
        if (null != user){
            response.addCookie(new Cookie("token",TokenUtil.getToken(user.getUiid())));
            return new Result<String>(200,"登录成功");
        }
        return new Result<String>(404,"账号或密码错误");
    }
}
