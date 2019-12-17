package com.iamvickyav.RateLimitApi.app.service;

import com.iamvickyav.RateLimitApi.data.repo.ApiRepo;
import com.iamvickyav.RateLimitApi.data.repo.UserApiQuotaRepo;
import com.iamvickyav.RateLimitApi.data.repo.UserRepo;
import com.iamvickyav.RateLimitApi.data.repo.UserSignupRepo;
import com.iamvickyav.RateLimitApi.domain.entity.*;
import com.iamvickyav.RateLimitApi.domain.exception.InvalidUserException;
import com.iamvickyav.RateLimitApi.domain.request.SignupRequest;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserSignupService {

    @Autowired
    UserSignupRepo signupRepo;

    @Autowired
    ApiRepo apiRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserApiQuotaRepo quotaRepo;

    @Autowired
    PasswordEncryptor passwordEncryptor;

    @Transactional
    public void processSignupRequest(SignupRequest request) {
        SignupInvite invite = signupRepo.findByUserEmailAndInvitationCode(request.getEmail(), request.getInvitationCode());
        if(invite == null) {
            throw new InvalidUserException("Can't Sign up the user; Invalid email/invitation code");
        } else {
            String encryptedPassword = passwordEncryptor.encryptPassword(request.getPassword());
            User user = new User(request.getUserName(), request.getEmail(), encryptedPassword);
            user = userRepo.save(user);
            List<Api> apiList = apiRepo.findAll();
            List<UserApiQuota> apiQuotaList = new ArrayList<>();
            for(Api api : apiList) {
                UserQuotaId quotaId = new UserQuotaId(user.getUserId(), api.getApiId());
                UserApiQuota userApiQuota = new UserApiQuota();
                userApiQuota.setId(quotaId);
                userApiQuota.setQuotaAssigned(api.getDefaultQuota());
                apiQuotaList.add(userApiQuota);
            }
            quotaRepo.saveAll(apiQuotaList);
        }
    }
}
