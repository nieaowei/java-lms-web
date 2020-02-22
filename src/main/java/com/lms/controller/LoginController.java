package com.lms.controller;

import com.lms.entity.UserEntity;
import com.lms.result.Result;
import com.lms.service.UserService;
import com.lms.utils.TokenUtil;
import com.lms.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/user/login")
    @ResponseBody
    public Result login(@RequestBody UserEntity requestUser,
                        HttpSession session,
                        HttpServletResponse response){
        UserVO userVO = new UserVO();
        String username = requestUser.getUsername();
        UserEntity user = userService.get(username,requestUser.getPassword());
        if (null == user){
            String msg = "账号或密码错误";
            return new Result(404,msg);
        }else{
            session.setAttribute("user", user);
            userVO.setUiid(user.getUiid());
            String token = TokenUtil.getToken(userVO);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new Result<String>(200,"登录成功", token);
        }
    }
}
