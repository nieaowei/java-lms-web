package com.lms.interception;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.utils.Result;
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
            System.out.println("非方法放行");
            return true;
        }
        /**
         * handler保存了本次请求的controller也就是接口方法的一些信息，如类，方法，参数等
         * 如果是一次静态资源的请求则该handler不应该是HandlerMethod的实现类
         * 判断是否是一个正常的接口，如果是则进行鉴权操作，不是则直接放行
         */
        HandlerMethod method = (HandlerMethod)handler;
        RequiredToken requiredToken = method.getMethod().getAnnotation(RequiredToken.class);

        RequiredAdmin requiredAdmin = method.getMethod().getAnnotation(RequiredAdmin.class);
        if (null == requiredAdmin){//没有注解放行
            System.out.println("无注解放行");
            if (null == requiredToken){//没有注解放行
                System.out.println("无注解放行");
                return true;
            }
        }
        //有注解，进行拦截
        if (null!=request){
                Cookie[] cookies = request.getCookies();
                if(null!=cookies){
                    for (Cookie cookie: cookies) {
                        if(COOKIE_KEY.equals(cookie.getName())){
                            if (requiredAdmin!=null){
                                Long uiidadmin = TokenUtil.validateTokenAdmin(cookie.getValue());
                                if (uiidadmin.equals(TokenUtil.EXPIRE_CODE)||uiidadmin.equals(TokenUtil.OTHER_CODE)){
                                    //todo 登录
                                    System.out.println("您不是管理员");
                                    response.setStatus(415);
                                    if (uiidadmin.equals(TokenUtil.EXPIRE_CODE)){
                                        //过期
                                        response.setStatus(302);
                                    }
//                                    ObjectMapper objectMapper = new ObjectMapper();
//                                    JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
//                                    response.getWriter().write( objectMapper.writeValueAsString(new Result<String>().setStatus(200).setMsg("您不是管理员，或您的登录凭证过期")));
                                    return false;
                                }else{
                                    request.setAttribute(RESULT_KEY,uiidadmin);
                                    System.out.println("有效请求");
//                                    Cookie cookie1 = new Cookie("token",TokenUtil.getTokenForAdmin(uiidadmin));
//                                    response.addCookie(cookie1);
                                    return true;
                                }
                            }
                            Long uiid = TokenUtil.validateTokenUser(cookie.getValue());
                            Long uiidadmin = TokenUtil.validateTokenAdmin(cookie.getValue());

                            if (uiid.equals(TokenUtil.EXPIRE_CODE)||uiid.equals(TokenUtil.OTHER_CODE)){
                                //todo 登录
                                System.out.println("登录凭证过期或无效");
                                response.setStatus(302);
                            }else {
                                request.setAttribute(RESULT_KEY,uiid);
                                response.setStatus(200);
                                System.out.println("有效请求");
//                                Cookie cookie1 = new Cookie("token",TokenUtil.getTokenForUser(uiid));
//                                response.addCookie(cookie1);
                                return true;
                            }
                            if (uiidadmin.equals(TokenUtil.EXPIRE_CODE)||uiidadmin.equals(TokenUtil.OTHER_CODE)){
                                //todo 登录
                                System.out.println("登录凭证过期或无效");
                                response.setStatus(302);
                                return false;
                            }else{
                                request.setAttribute(RESULT_KEY,uiidadmin);
                                response.setStatus(200);
                                System.out.println("有效请求");
//                                Cookie cookie1 = new Cookie("token",TokenUtil.getTokenForAdmin(uiidadmin));
//                                response.addCookie(cookie1);
                                return true;
                            }

                        }
                    }
                }
            }
        return false;
    }
}
