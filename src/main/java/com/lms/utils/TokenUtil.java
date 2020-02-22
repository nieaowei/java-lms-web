package com.lms.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Date;
import java.util.List;

public class TokenUtil {

    private static final long EXPIRE_TIME = 2 * 60 * 60;// 过期时间戳
    private static final long FAIL_TIME = 24 * 60 * 60;// 失效时间
    private static final String KEY = "leaningmanagersystem";
    private static final String ISSUER = "lms";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(KEY);
    private static final Long EXPIRE_CODE = (long) -1;
    private static final Long OTHER_CODE = (long) -2;

    /**
     * 根据UIID获取令牌
     * @param uiid
     * @return
     */
    public static String getToken(Long uiid) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        try{
            return JWT.create()
                    .withAudience(uiid.toString())
                    .withIssuer(ISSUER)
                    .withIssuedAt(now)
                    .withExpiresAt(new Date(now.getTime()+100))
                    .sign(ALGORITHM);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检验token
     * @param token
     * @return
     */
    public static Long validateToken(String token){
        String auther = "-1";
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).withIssuer(ISSUER).build();
            verifier.verify(token);
            List<String> authers = JWT.decode(token).getAudience();
            if (authers.size() != 1){
                return OTHER_CODE;
            }else{
                auther=authers.get(0);
            }
        }catch (TokenExpiredException e){
            return EXPIRE_CODE;
        } catch (Exception e){
            return OTHER_CODE;
        }
        return Long.parseLong(auther.toString());
    }

    public boolean saveToken(){
        return false;
    }

}
