package com.lms.controller;


import com.lms.entity.UserEntity;
import com.lms.interception.RequiredToken;
import com.lms.service.UserService;
import com.lms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/changepd")
    @RequiredToken
    public Result<String> changepd(@RequestBody UserEntity requestUser){
        UserEntity user = userService.getByName(requestUser.getUsername());
        if(!userService.isPassWordCompliance(requestUser)){
            return new Result<String>().setStatus(202).setMsg("密码不符合要求");
        }
        user.setPassword(requestUser.getPassword());
        userService.save(user);
        return new Result<String>().setStatus(200).setMsg("修改成功");
    }
}
