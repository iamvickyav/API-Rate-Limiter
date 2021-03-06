package com.iamvickyav.RateLimitApi.app.config;

import com.iamvickyav.RateLimitApi.app.interceptor.LoggingInterceptor;
import com.iamvickyav.RateLimitApi.app.interceptor.RateLimitInterceptor;
import com.iamvickyav.RateLimitApi.app.interceptor.UserAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RateLimitWebConfig implements WebMvcConfigurer {

    @Autowired
    UserAuthenticationInterceptor userAuthenticationInterceptor;

    @Autowired
    RateLimitInterceptor rateLimitInterceptor;

    @Autowired
    LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
        registry.addInterceptor(userAuthenticationInterceptor).addPathPatterns("/countries/**").addPathPatterns("/country/**");
        registry.addInterceptor(rateLimitInterceptor).addPathPatterns("/countries/**").addPathPatterns("/country/**");
    }
}
