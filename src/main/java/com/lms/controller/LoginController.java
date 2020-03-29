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
            //登录成功--- 签发token
            Cookie cookie;
            String msg;
            if (user.getAdmin()){
                cookie= new Cookie("token", TokenUtil.getTokenForAdmin(user.getUiid()));
                msg = "登录成功,您是管理员";
            }else {
                cookie= new Cookie("token", TokenUtil.getTokenForUser(user.getUiid()));
                msg = "登录成功";
            }
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge((int)TokenUtil.EXPIRE_TIME);
            response.addCookie(cookie);
            //有注解 不需要特别设定
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
            return new Result<String>().setStatus(200).setMsg(msg).setData(user.getUsername());
        }
        return new Result<String>().setStatus(400).setMsg("登录失败");
    }
}
