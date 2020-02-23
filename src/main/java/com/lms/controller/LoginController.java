package com.lms.controller;

import com.lms.annotation.AuthToken;
import com.lms.entity.UserEntity;
import com.lms.result.Result;
import com.lms.service.UserService;
import com.lms.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/login")
    public Result<String> login(@RequestBody UserEntity requestUser, HttpServletResponse response){
        String username = requestUser.getUsername();
        UserEntity user = userService.get(username,requestUser.getPassword());
        if (null != user){
            Cookie cookie= new Cookie("token",TokenUtil.getToken(user.getUiid()));
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge((int)TokenUtil.EXPIRE_TIME);
            response.addCookie(cookie);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            return new Result<String>(200,"登录成功");
        }
        return new Result<String>(404,"账号或密码错误");
    }
    /**
     * 测试权限访问
     */
    @GetMapping(value = "test")
    @AuthToken
    public ModelAndView test(){
        return new ModelAndView("success");
    }
}
