package com.lms.utils;

import ch.qos.logback.core.util.DatePatternToRegexUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.lms.entity.UserEntity;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

import java.util.Date;

public class TokenUtil {

    private static long EXPIRE_TIME = 2 * 60 * 60;// 过期时间戳
    private static long FAIL_TIME = 24 * 60 * 60;// 失效时间

    /**
     * 根据UIID获取令牌
     * @param uiid
     * @return
     */
    public static String getToken(String uiid) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expireTime = new Date(nowMillis + EXPIRE_TIME);
        try{
            Algorithm algorithm = Algorithm.HMAC256(String.valueOf(FAIL_TIME));
            return JWT.create()
                    .withIssuer(uiid)
                    .withIssuedAt(now)
                    .withExpiresAt(expireTime)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检验token
     * @param uiid
     * @param token
     * @return
     */
    private static boolean validateToken(String uiid, String token){
        boolean active = true;
        try {
            Algorithm algorithm = Algorithm.HMAC256(String.valueOf(FAIL_TIME));
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(uiid).build();
            verifier.verify(token);
        }catch (Exception e){
            active = false;
        }
        return active;
    }

    public boolean saveToken(){
        return false;
    }
}
