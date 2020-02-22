package com.lms.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lms.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenDao extends JpaRepository<Token, Long> {
    /**
     * 根据token查阅token信息
     * @param token
     * @return
     */
    Token findByToken(String token);
}
