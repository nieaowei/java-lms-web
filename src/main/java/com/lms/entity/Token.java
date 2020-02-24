package com.lms.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户token
 */
//@Entity
@Data
//@Table("token")
@Builder
public class Token implements Serializable {

    private long uiid;
    private String token;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;

    public Token(){

    }

    public Token(long uiid, String token, LocalDateTime expireTime, LocalDateTime updateTime) {
        this.uiid = uiid;
        this.token = token;
        this.expireTime = expireTime;
        this.updateTime = updateTime;
    }
}
