package com.iamvicky.RateLimitApi.redis.repo;

import com.iamvickyav.RateLimitApi.domain.exception.LimitExceededException;
import com.iamvickyav.RateLimitApi.domain.redis.RedisUserEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepoImpl implements RedisRepo {

    private static final Logger logger = LogManager.getLogger(RedisRepoImpl.class);

    RedisTemplate<String, RedisUserEntry> redisTemplate;

    HashOperations<String, String, String> hashOperations;

    RedisRepoImpl(RedisTemplate<String, RedisUserEntry> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public String getUsage(String redisKey) {
        return hashOperations.get(redisKey, "usage");
    }

    @Override
    public void checkLimit(String redisKey, String userName, String usage) {
        String max = hashOperations.get(redisKey, "maxLimit");
        if (max != null) {
            int visitCount = Integer.parseInt(usage);
            int maxLimit = Integer.parseInt(max);
            if (visitCount < maxLimit) {
                logger.info("RateLimit not exceeded for clientId=" + userName);
                hashOperations.increment(redisKey, "usage", 1L);
            } else {
                logger.info("RateLimit exceeded for clientId=" + userName);
                throw new LimitExceededException("RateLimit exceeded for clientId=" + userName);
            }
        }
    }
}
