package com.lms.interception;

import com.lms.entity.Token;
import com.lms.utils.TokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuthInterceptor implements HandlerInterceptor {
    final static String COOKIE_KEY="token";
    public final static String RESULT_KEY = "uiid";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截处理");
        //不是处理方法，放行
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        /**
         * handler保存了本次请求的controller也就是接口方法的一些信息，如类，方法，参数等
         * 如果是一次静态资源的请求则该handler不应该是HandlerMethod的实现类
         * 判断是否是一个正常的接口，如果是则进行鉴权操作，不是则直接放行
         */
        HandlerMethod method = (HandlerMethod)handler;
        RequiredToken requiredToken = method.getMethod().getAnnotation(RequiredToken.class);
        if (null == requiredToken){//没有注解放行
            return true;
        }
        //有注解，进行拦截
        if (null!=request){
                Cookie[] cookies = request.getCookies();
                if(null!=cookies){
                    for (Cookie cookie: cookies) {
                        if(COOKIE_KEY.equals(cookie.getName())){
                            Long uiid = TokenUtil.validateToken(cookie.getValue());
                            if (uiid.equals(TokenUtil.EXPIRE_CODE)||uiid.equals(TokenUtil.OTHER_CODE)){
                                //todo 登录
                                return false;
                            }
                            request.setAttribute(RESULT_KEY,uiid);
                            System.out.println("有效请求");
                            return true;
                        }
                    }
                }
            }
        return false;
    }
}
