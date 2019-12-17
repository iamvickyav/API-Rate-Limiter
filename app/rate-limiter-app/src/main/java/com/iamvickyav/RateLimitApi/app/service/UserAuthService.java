package com.iamvickyav.RateLimitApi.app.service;

import com.iamvickyav.RateLimitApi.data.repo.UserRepo;
import com.iamvickyav.RateLimitApi.domain.entity.User;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    @Autowired
    PasswordEncryptor passwordEncryptor;

    @Autowired
    UserRepo userRepo;

    public boolean processUserAuthentication(String userName, String userPassword) {
        boolean isAuthenticUser = false;
        User user = userRepo.findByUserName(userName);
        if (user != null) {
            String encryptedPass = user.getUserPassword();
            isAuthenticUser = passwordEncryptor.checkPassword(userPassword, encryptedPass);
        }
        return isAuthenticUser;
    }
}
