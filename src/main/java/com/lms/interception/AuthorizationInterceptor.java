package com.lms.interception;

import com.lms.annotation.AuthToken;
import com.lms.entity.Token;
import com.lms.utils.TokenUtil;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token验证
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    // 存放鉴权信息的header名称，默认为Authorization
    private final String HTTP_HEADER_NAME = "token";
    // 鉴权失败后返回的错误信息，默认为401 unauthorized
    private final String UN_AUTHORIZED_ERROR_MESSAGE = "401 unauthorized";
    // 鉴权失败后返回的HTTP错误码，默认为401
    private final int UN_AUTHORIZED_ERROR_CODE = HttpServletResponse.SC_UNAUTHORIZED;
    // 存放登录用户模型key的Request Key
    private static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 凡是用了AuthToken注解的都需要验证token
        if (method.getAnnotation(AuthToken.class) != null
        || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null){
            String token = request.getParameter(HTTP_HEADER_NAME);
            String uiid = "";
            Jedis jedis = new Jedis("localhost", 6379);
            if (token != null || token.length() != 0){
                uiid = jedis.get(token);
            }
            if (uiid != null || !uiid.trim().equals("")){
                jedis.expire(uiid, (int) TokenUtil.EXPIRE_TIME);
                jedis.expire(token, (int) TokenUtil.EXPIRE_TIME);
                jedis.close();
                request.setAttribute(REQUEST_CURRENT_KEY, uiid);
            }
        }
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }
}
