package com.iamvickyav.RateLimitApi.app.interceptor;

import com.iamvickyav.RateLimitApi.app.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Autowired
    RateLimitService rateLimitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientId = request.getHeader("CLIENT_ID");
        String apiName = ((HandlerMethod) handler).getMethod().getName();
        rateLimitService.checkLimit(clientId, apiName);
        return true;
    }
}
