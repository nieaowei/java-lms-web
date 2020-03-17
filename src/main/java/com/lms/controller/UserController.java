package com.lms.controller;

import com.alibaba.fastjson.JSONObject;
import com.lms.entity.UserEntity;
import com.lms.interception.AuthInterceptor;
import com.lms.interception.RequiredToken;
import com.lms.service.UserService;
import com.lms.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


@RestController

public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取个人信息
     *
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/user/profile")
    @RequiredToken
    public Result<UserEntity> profile(HttpServletRequest request) {
        UserEntity userEntity;
        try {
            userEntity = userService.findByUiid((long) request.getAttribute("uiid"));
            userEntity.setPassword("");//清除密码
        } catch (Exception e) {
            return new Result<UserEntity>().setStatus(500).setMsg("获取个人信息失败");
        }
        return new Result<UserEntity>().setStatus(200).setMsg("获取个人信息成功").setData(userEntity);
    }


    /***
     * 修改密码
     * @param request 获取请求中的token所解析的uiid和新旧密码
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/user/changepd")
    @RequiredToken
    public Result<String> changepd(@RequestBody JSONObject jsonParam, HttpServletRequest request) {
        Long uiid = (long) request.getAttribute(AuthInterceptor.RESULT_KEY);
        // 因为数据库不可空，所以无需验资字符串是否为空
        String oldpassword = jsonParam.getString("oldpassword");
        String newpassword = jsonParam.getString("newpassword");
        //验明身份
        UserEntity user = userService.getByUiidAndPassword(uiid, oldpassword);
        if (null == user) {
            //验证失败
            return new Result<String>().setStatus(500).setMsg("旧密码错误");
        }
        //验证新密码强度要求
        if (!userService.isPassWordCompliance(user.setPassword(newpassword))) {
            //验证失败
            return new Result<String>().setStatus(500).setMsg("新密码不符合要求");
        }
        //符合要求 存储数据
        try {
            userService.save(user);
        } catch (Exception e) {
            return new Result<String>().setStatus(500).setMsg("修改密码失败");
        }
        return new Result<String>().setStatus(200).setMsg("修改密码成功");
    }

    @Value("${web.upload_path}")
    private String uploadUrl;

    @Value("${web.domain}")
    private String domain;

    @CrossOrigin
    @PostMapping(value = "/admin/uploadimage")
//    @RequiredToken
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {

            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            fileName = DigestUtils.md5DigestAsHex(file.getInputStream());
            String path = uploadUrl + "/images/" + fileName + suffix;
            File dest = new File(path);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return new Result<String>().setStatus(200).setMsg("上传成功").setData("http://" + domain + "/images/" + fileName + suffix);
        } catch (Exception e) {
            return new Result<String>().setMsg("上传失败").setStatus(500);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/admin/uploadpdf")
//    @RequiredToken
    public Result<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        try {

            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            fileName = DigestUtils.md5DigestAsHex(file.getInputStream());
            String path = uploadUrl + "/pdfs/" + fileName + suffix;
            File dest = new File(path);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return new Result<String>().setStatus(200).setMsg("上传成功").setData("http://" + domain + "/pdfs/" + fileName + suffix);
        } catch (Exception e) {
            return new Result<String>().setMsg("上传失败").setStatus(500);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/admin/uploadvideo")
//    @RequiredToken
    public Result<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        try {

            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf('.'));
            fileName = DigestUtils.md5DigestAsHex(file.getInputStream());
            String path = uploadUrl + "/videos/" + fileName + suffix;
            File dest = new File(path);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return new Result<String>().setStatus(200).setMsg("上传成功").setData("http://" + domain + "/videos/" + fileName + suffix);
        } catch (Exception e) {
            return new Result<String>().setMsg("上传失败").setStatus(500);
        }
    }

}
