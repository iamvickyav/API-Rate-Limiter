package com.iamvickyav.RateLimitApi.app.service;

import com.iamvickyav.RateLimitApi.app.controller.WorldInformationController;
import com.iamvickyav.RateLimitApi.data.repo.ApiRepo;
import com.iamvickyav.RateLimitApi.data.repo.UserApiQuotaRepo;
import com.iamvickyav.RateLimitApi.data.repo.UserRepo;
import com.iamvickyav.RateLimitApi.domain.entity.User;
import com.iamvickyav.RateLimitApi.domain.entity.UserApiQuota;
import com.iamvickyav.RateLimitApi.domain.entity.UserQuotaId;
import com.iamvickyav.RateLimitApi.domain.request.QuotaUpdateRequest;
import com.iamvickyav.RateLimitApi.domain.request.UserQuotaUpdateRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminService {

    private static final Logger logger = LogManager.getLogger(AdminService.class);

    @Autowired
    UserApiQuotaRepo quotaRepo;

    @Autowired
    ApiRepo apiRepo;

    @Autowired
    UserRepo userRepo;

    @Async
    @Transactional
    public void processRequest(UserQuotaUpdateRequest userQuotaUpdateRequest) {
        Map<Integer, List<QuotaUpdateRequest>> request = userQuotaUpdateRequest.request;
        Set<Integer> userIds = request.keySet();
        for(Integer userId: userIds) {
            Optional<User> userOptional = userRepo.findById(userId);
            if(userOptional.isPresent()) {
                List<QuotaUpdateRequest> quotaUpdateRequestList = request.get(userId);
                for(QuotaUpdateRequest updateRequest: quotaUpdateRequestList) {
                    Optional<UserApiQuota> apiQuotaOptional = quotaRepo.findById(new UserQuotaId(userId, updateRequest.apiId));
                    if(apiQuotaOptional.isPresent()){
                        logger.info("Updating Quota for userId="+ userId +", apiId="+ updateRequest.apiId + "newQuota="+updateRequest.newQuota);
                        quotaRepo.updateUserQuota(updateRequest.apiId, userId, updateRequest.newQuota);
                    } else {
                        logger.info("No Quota exist, Creating Quota for userId="+ userId +", apiId="+ updateRequest.apiId + "quota="+updateRequest.newQuota);
                        quotaRepo.save(new UserApiQuota(updateRequest.apiId, userId, updateRequest.newQuota));
                    }
                }
            } else {
                logger.info("Invalid userId="+ userId +", Can't update quota");
            }
        }
    }
}