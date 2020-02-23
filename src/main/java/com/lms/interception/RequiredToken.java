package com.lms.interception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})//注解的范围是类、接口、枚举的方法上
@Retention(RetentionPolicy.RUNTIME)//被虚拟机保存，可用反射机制读取
public @interface RequiredToken {

}
