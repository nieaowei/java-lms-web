package com.lms.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lms.entity.UserEntity;

public class TokenUtil {
    public String getToken(UserEntity user) {
        String token="";
        token= JWT.create().withAudience(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
    public boolean saveToken(){
        return false;
    }
}
