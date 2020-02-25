package com.lms.controller;


import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.UserService;
import com.lms.utils.Result;
import org.apache.ibatis.annotations.Param;
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
    public Result<String> changepd(HttpServletRequest request,
                                   @RequestParam("oldpassword") String oldPassword,
                                   @RequestParam("newpassword") String newPasswrod){
        Long uiid = (long)request.getAttribute(AuthInterceptor.RESULT_KEY);
        UserEntity user = userService.findByUiidAndPassword(uiid, oldPassword);
        if (user != null){
            user.setPassword(newPasswrod);
            if(!userService.isPassWordCompliance(user)){
                return new Result<String>().setStatus(202).setMsg("密码不符合要求");
            }
        }
//        user.setPassword(user.getPassword());
        userService.save(user);
        return new Result<String>().setStatus(200).setMsg("修改成功");
    }
}
