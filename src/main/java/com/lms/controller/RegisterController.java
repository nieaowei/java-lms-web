package com.lms.controller;

import com.lms.entity.UserEntity;
import com.lms.vo.ResultVO;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/register")
    @ResponseBody
    public ResultVO<String> register(@RequestBody UserEntity requestUser){
        UserEntity user = new UserEntity();
        user.setUsername(requestUser.getUsername());
        user.setPassword(requestUser.getPassword());
        user.setPhonenum(requestUser.getPhonenum());
        int status = 200;
        String msg = "注册成功";
        if(!userService.isPassWordCompliance(user)){
            status = 201;
            msg = "密码不合格，应为8-16位数字字母混合";
        }
        else if(!userService.isPhoneNumCompliance(user)){
            status = 202;
            msg = "输入的手机号不是一个正确的手机号";
        }
        else{userService.save(user);}
        return new ResultVO(status,msg);
    }
}
