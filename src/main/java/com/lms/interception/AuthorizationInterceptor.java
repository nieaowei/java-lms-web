package com.lms.interception;

import com.lms.annotation.Login;
import com.lms.entity.Token;
import com.lms.service.TokenService;
import com.lms.utils.TokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * token验证
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Resource
    private TokenService tokenService;
    public final static String UIID = "uiid";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        //如果处理对象是一个处理方法，则获取到方法上的注解
        if (handler instanceof HandlerMethod){
            annotation = ((HandlerMethod)handler).getMethodAnnotation(Login.class);
            //否则直接放过拦截的请求
        }else {
            return true;
        }
        //此方法没有Login注解
        if (annotation == null){
            return true;
        }
        //从Http请求头获取token
        String token = request.getHeader("token");
        //如果请求头中没有token，则从请求参数获取
        if (StringUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        //如果还是没有token，则抛出异常
        if (StringUtils.isEmpty(token)){
            throw new Exception("没有token");
        }
        Token tokenEntity = tokenService.findByToken(token);
        // 查询token信息是否为null或者过期，则抛出异常
//        if (tokenEntity == null || tokenEntity.getExpireTime().isBefore(LocalDateTime.now())){
//            throw new Exception("token为null或者过期");
//        }
        /*if (!TokenUtil.validateToken(String.valueOf(tokenEntity.getUiid()), token)){
            throw new Exception("token验证失败");
        }
        //否则，存入request，后根据UIID获得用户信息
        request.setAttribute(UIID, tokenEntity.getUiid());
        */return true;
    }
}
