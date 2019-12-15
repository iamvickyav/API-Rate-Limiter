package com.iamvicky.RateLimitApi.redis.repo;

import com.iamvickyav.RateLimitApi.domain.exception.LimitExceededException;
import com.iamvickyav.RateLimitApi.domain.redis.RedisUserEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

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
    public void checkLimit(String redisKey, Integer maxLimit) {
        String counter = hashOperations.get(redisKey, "h");
        if (counter != null) {
            int visitCount = Integer.parseInt(counter);
            if (visitCount < maxLimit) {
                logger.info("RateLimit not exceeded for clientId=" + redisKey);
                hashOperations.increment(redisKey, "h", 1L);
            } else {
                logger.info("RateLimit exceeded for clientId=" + redisKey);
                throw new LimitExceededException("RateLimit exceeded for clientId=" + redisKey);
            }
        } else {
            logger.info("No entry exist in Redis; Creating new entry for clientId=" + redisKey);
            hashOperations.put(redisKey, "h", "0");
            redisTemplate.expire(redisKey, 60, TimeUnit.SECONDS);
        }
    }
}
