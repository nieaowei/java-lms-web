package com.lms.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Date;
import java.util.List;

public class TokenUtil {

    public static final long EXPIRE_TIME = 30*60 * 1000;// 过期时间，30分钟
    public static final String KEY = "leaningmanagersystem";
    public static final String ISSUER = "lms";//签发机构，用于校验
    public static final String ADMIN_ISSUER = "admin";//管理员签证
    public static final Algorithm ALGORITHM = Algorithm.HMAC256(KEY); //签发秘钥，用于校验
    public static final Long EXPIRE_CODE = (long) -1;
    public static final Long OTHER_CODE = (long) -2;

    /**
     * 根据UIID获取令牌
     * @param uiid
     * @return
     */
    public static String getTokenForUser(Long uiid) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        try{
            return JWT.create()
                    .withAudience(uiid.toString())
                    .withIssuer(ISSUER)
                    .withIssuedAt(now)
                    .withExpiresAt(new Date(now.getTime()+EXPIRE_TIME))
                    .sign(ALGORITHM);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String getTokenForAdmin(Long uiid) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        try{
            return JWT.create()
                    .withAudience(uiid.toString())
                    .withIssuer(ADMIN_ISSUER)
                    .withIssuedAt(now)
                    .withExpiresAt(new Date(now.getTime()+EXPIRE_TIME))
                    .sign(ALGORITHM);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 检验token
     * @param token
     * @return 返回用户id
     */
    public static Long validateTokenUser(String token){
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

    public static Long validateTokenAdmin(String token){
        String auther = "-1";
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).withIssuer(ADMIN_ISSUER).build();
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
