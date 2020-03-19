package com.lms.config;

import com.lms.interception.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        assert registry != null;
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").addPathPatterns("/***");
    }
}
