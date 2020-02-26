package com.lms.controller;

import com.lms.entity.UserEntity;
import com.lms.service.UserService;
import com.lms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/register")
    @ResponseBody
    public Result<String> register(@RequestBody UserEntity requestUser){
        UserEntity user = new UserEntity();
        user.setUsername(requestUser.getUsername());
        user.setPassword(requestUser.getPassword());
        user.setPhonenum(requestUser.getPhonenum());
        if(!userService.isPassWordCompliance(user)){
            return new Result<String>().setStatus(500).setMsg("密码不符合要求。");
        }
        if(!userService.isPhoneNumCompliance(user)){
            return new Result<String>().setStatus(500).setMsg("手机号码格式错误");
        }
        try{
            UserEntity userEntity=userService.save(user);
        }catch (Exception e){
            if(e.getMessage().contains("username")){
                return new Result<String>().setStatus(500).setMsg("用户已存在，注册失败");
            }
            if (e.getMessage().contains("phonenum")){
                return new Result<String>().setStatus(500).setMsg("手机号已存在，注册失败");
            }
            return new Result<String>().setStatus(500).setMsg("注册失败");
        }
        return new Result<String>().setStatus(200).setMsg("注册成功");
    }
}
