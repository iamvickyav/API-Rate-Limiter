package com.iamvickyav.RateLimitApi.app.interceptor;

import com.iamvickyav.RateLimitApi.app.service.UserAuthService;
import com.iamvickyav.RateLimitApi.domain.exception.InvalidUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(UserAuthenticationInterceptor.class);

    @Autowired
    UserAuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userName = request.getHeader("CLIENT_ID");
        String userPassword = request.getHeader("CLIENT_SECRET");

        boolean isUserAuthentic = authService.processUserAuthentication(userName, userPassword);
        if(isUserAuthentic) {
            logger.info("User Validation Successful for clientId="+userName);
        } else {
            logger.info("User Validation Failed for clientId="+userName);
            throw new InvalidUserException("Either CLIENT_ID or CLIENT_SECRET is incorrect");
        }
        return true;
    }
}
