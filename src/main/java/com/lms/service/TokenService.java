package com.lms.service;

import com.lms.dao.TokenDao;
import com.lms.entity.Token;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {
    @Resource
    private TokenDao tokenDao;
    /**
     * 12小时过期 单位：毫秒
     */
    private final static int EXPIRE = 3600 * 12 * 1000;

    public Token findByToken(String token){
        return tokenDao.findByToken(token);
    }

    public Token createToken(long uiid){
        //得到当前时间
        LocalDateTime now = LocalDateTime.now();
        //根据过期时间加上当前时间得到token的有效时间
        long indate = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() + EXPIRE;
        LocalDateTime tokenExpireDateTime = LocalDateTime.ofInstant(new Date(indate).toInstant(), ZoneId.systemDefault());
        String token = generateToken();
        Token tokenEntity = Token.builder().expireTime(tokenExpireDateTime).uiid(uiid).token(token).updateTime(now).build();
        tokenDao.save(tokenEntity);
        return tokenEntity;
    }

    public void expireToken(long uiid){
        LocalDateTime now = LocalDateTime.now();
        Token token = Token.builder().uiid(uiid).expireTime(now).updateTime(now).build();
        tokenDao.save(token);
    }

    /**
     * 生成token
     * @return
     */
    public String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
