package com.iamvickyav.RateLimitApi.app.interceptor;

import com.iamvicky.RateLimitApi.redis.repo.RedisRepo;
import com.iamvicky.RateLimitApi.redis.repo.RedisRepoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(RateLimitInterceptor.class);

    @Autowired
    RedisRepoImpl redisRepoImpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("RateLimitInterceptor called");
        String clientId = request.getHeader("CLIENT_ID");
        checkIfLimitExceed(clientId, ((HandlerMethod) handler).getMethod().getName());
        return true;
    }

    private void checkIfLimitExceed(String clientId, String methodName) {
        String limitKey = clientId + "-" + methodName;
        redisRepoImpl.checkLimit(limitKey);
    }
}
