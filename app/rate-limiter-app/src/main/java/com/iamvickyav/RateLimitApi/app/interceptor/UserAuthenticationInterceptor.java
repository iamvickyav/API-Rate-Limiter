package com.iamvickyav.RateLimitApi.app.interceptor;

import com.iamvickyav.RateLimitApi.domain.InvalidUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(UserAuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.debug("UserAuthenticationInterceptor called");
        String clientId = request.getHeader("CLIENT_ID");
        String clientSecret = request.getHeader("CLIENT_SECRET");
        checkIfUserIsValid(clientId, clientSecret);
        return true;
    }

    private void checkIfUserIsValid(String clientId, String clientSecret) {
         if(clientId != null && clientId.equals("VICKY") && clientSecret != null && clientSecret.equals("KEERTHU")){
             logger.info("Valid User; clientId=" + clientId);
         } else {
             logger.info("Invalid User; clientId=" + clientId);
             throw new InvalidUserException("Not a valid User");
         }
    }
}
