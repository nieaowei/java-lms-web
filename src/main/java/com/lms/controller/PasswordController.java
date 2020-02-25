package com.lms.controller;


import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.UserService;
import com.lms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PasswordController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/changepd")
    @RequiredToken
    public Result<String> changepd(HttpServletRequest request){
        Long uiid = (long)request.getAttribute(AuthInterceptor.RESULT_KEY);
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        //验明身份
        UserEntity user = userService.getByUiidAndPassword(uiid,oldpassword);
        if (null != user){
            //验证失败
            return new Result<String>().setStatus(400).setMsg("旧密码错误");
        }
        //验证新密码强度要求
        if(!userService.isPassWordCompliance(user.setPassword(newpassword))){
            //验证失败
            return new Result<String>().setStatus(202).setMsg("新密码不符合要求");
        }
        //符合要求 存储数据
        userService.save(user);
        return new Result<String>().setStatus(200).setMsg("修改成功");
    }
}
