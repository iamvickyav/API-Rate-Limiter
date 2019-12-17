package com.iamvickyav.RateLimitApi.app.service;

import com.iamvicky.RateLimitApi.redis.repo.RedisRepo;
import com.iamvickyav.RateLimitApi.data.repo.ApiRepo;
import com.iamvickyav.RateLimitApi.data.repo.UserApiQuotaRepo;
import com.iamvickyav.RateLimitApi.data.repo.UserRepo;
import com.iamvickyav.RateLimitApi.domain.entity.Api;
import com.iamvickyav.RateLimitApi.domain.entity.User;
import com.iamvickyav.RateLimitApi.domain.entity.UserApiQuota;
import com.iamvickyav.RateLimitApi.domain.entity.UserQuotaId;
import com.iamvickyav.RateLimitApi.domain.enums.Quota;
import com.iamvickyav.RateLimitApi.domain.exception.UnsupportedApiException;
import com.iamvickyav.RateLimitApi.domain.exception.UserNotAuthorisedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RateLimitService {

    private static final Logger logger = LogManager.getLogger(RateLimitService.class);

    RedisRepo redisRepo;
    RedisTemplate<String, String> redisTemplate;
    HashOperations<String, String, String> hashOperations;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ApiRepo apiRepo;

    @Autowired
    UserApiQuotaRepo quotaRepo;

    RateLimitService(RedisRepo redisRepo, RedisTemplate<String, String> redisTemplate) {
        this.redisRepo = redisRepo;
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void checkLimit(String userName, String apiName) {
        String redisKey = userName + "-" + apiName;
        String usage = redisRepo.getUsage(redisKey);

        if (usage != null) {
            redisRepo.checkLimit(redisKey, userName, usage);
        } else {
            logger.info("No entry exist in Redis; Creating new entry for clientId=" + userName);
            createEntryInRedis(userName, apiName, redisKey);
            logger.info("Redis write completed for clientId=" + userName + " with redisKey="+redisKey);
        }
    }

    private void createEntryInRedis(String userName, String apiName, String redisKey) {
        User user = userRepo.findByUserName(userName);
        Api api = apiRepo.findByApiName(apiName);
        if (api == null) {
            throw new UnsupportedApiException("Call to invalid API");
        }
        Optional<UserApiQuota> quota = quotaRepo.findById(new UserQuotaId(user.getUserId(), api.getApiId()));
        if (!quota.isPresent()) {
            throw new UserNotAuthorisedException("User has no access to the API");
        }
        writeToRedis(redisKey, api, quota.get());
    }

    private void writeToRedis(String redisKey, Api api, UserApiQuota quota) {
        hashOperations.put(redisKey, "usage", "1");
        hashOperations.put(redisKey, "maxLimit", quota.getQuotaAssigned().toString());
        if (api.getQuotaWindow() == Quota.EVERY_HOUR)
            redisTemplate.expire(redisKey, 1, TimeUnit.HOURS);
        else
            redisTemplate.expire(redisKey, 1, TimeUnit.MINUTES);
    }
}
