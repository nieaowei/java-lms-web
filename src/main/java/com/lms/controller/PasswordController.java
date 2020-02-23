package com.lms.controller;


import com.lms.entity.UserEntity;
import com.lms.result.Result;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PasswordController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/changepd")
    @ResponseBody
    public Result changepd(@RequestBody UserEntity requestUser){
        int status = 200;
        String msg = "修改成功";

        UserEntity user = userService.getByName(requestUser.getUsername());
        if(!userService.isPassWordCompliance(requestUser)){
            status = 201;
            msg = "密码不合格，应为8-16位数字字母混合";
        }
        else{
            user.setPassword(requestUser.getPassword());
            userService.save(user);
        }
        return new Result(status,msg);
    }
}
