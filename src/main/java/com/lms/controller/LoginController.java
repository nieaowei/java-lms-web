package com.lms.controller;

import com.lms.annotation.AuthToken;
import com.lms.entity.UserEntity;
import com.lms.result.Result;
import com.lms.service.UserService;
import com.lms.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/login")
    @ResponseBody
    public Result<String> login(@RequestBody UserEntity requestUser, HttpServletResponse response){
        String username = requestUser.getUsername();
        UserEntity user = userService.get(username,requestUser.getPassword());
        if (null != user){
            String token = TokenUtil.getToken(user.getUiid());
            response.addCookie(new Cookie("token",token));
            Jedis jedis = new Jedis("localhost", 6370);
            jedis.expire(String.valueOf(user.getUiid()), (int) TokenUtil.EXPIRE_TIME);
            jedis.set(token, String.valueOf(user.getUiid()));
            jedis.expire(token, (int) TokenUtil.EXPIRE_TIME);
            jedis.close();
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
