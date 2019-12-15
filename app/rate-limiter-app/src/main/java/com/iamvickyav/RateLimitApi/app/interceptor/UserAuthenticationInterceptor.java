package com.iamvickyav.RateLimitApi.app.interceptor;

import com.iamvickyav.RateLimitApi.data.repo.UserRepo;
import com.iamvickyav.RateLimitApi.domain.entity.UserPassword;
import com.iamvickyav.RateLimitApi.domain.exception.InvalidUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(UserAuthenticationInterceptor.class);

    @Autowired
    PasswordEncryptor passwordEncryptor;

    @Autowired
    UserRepo userRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId = request.getHeader("CLIENT_ID");
        String clientSecret = request.getHeader("CLIENT_SECRET");

        checkIfUserIsValid(userId, clientSecret);
        return true;
    }

    private void checkIfUserIsValid(String userId, String password) {
         UserPassword passwordFromDB = userRepo.findByUserId(userId);
        if(passwordFromDB != null){
             String encryptedPass = passwordFromDB.getUserPassword();
             boolean isValid = passwordEncryptor.checkPassword(password, encryptedPass);
             if(isValid) {
                 logger.info("User Validation Successful for clientId=" + userId);
             } else {
                 logger.info("User Validation Failed for clientId="+userId);
                 throw new InvalidUserException("Either CLIENT_ID or CLIENT_SECRET is incorrect");
             }
         } else {
             logger.info("User Validation Failed for clientId="+userId);
             throw new InvalidUserException("Either CLIENT_ID or CLIENT_SECRET is incorrect");
         }
    }
}
