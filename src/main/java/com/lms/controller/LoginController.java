package com.lms.controller;

import com.lms.entity.UserEntity;
import com.lms.utils.Result;
import com.lms.service.UserService;
import com.lms.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/login")
    //因为user实体全是字符串类型字段，所以可以使用自动映射
    public Result<String> login(@RequestBody UserEntity requestUser, HttpServletResponse response){
        UserEntity user = userService.get(requestUser.getUsername(),requestUser.getPassword());
        if (null != user){
            Cookie cookie= new Cookie("token",TokenUtil.getToken(user.getUiid()));
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge((int)TokenUtil.EXPIRE_TIME);
            response.addCookie(cookie);
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
            return new Result<String>().setStatus(200).setMsg("登录成功");
        }
        return new Result<String>().setStatus(400).setMsg("登录失败");
    }
}
