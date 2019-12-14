package com.iamvicky.RateLimitApi.redis.repo;

import com.iamvickyav.RateLimitApi.domain.LimitExceededException;
import com.iamvickyav.RateLimitApi.domain.RedisUserEntry;
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
    public void checkLimit(String id) {
        String counter = hashOperations.get(id, "h");
        if (counter != null) {
            int visitCount = Integer.parseInt(counter);
            if (visitCount < 10) {
                logger.info("RateLimit not exceeded for clientId=" + id);
                hashOperations.increment(id, "h", 1L);
            } else {
                logger.info("RateLimit exceeded for clientId=" + id);
                throw new LimitExceededException("RateLimit exceeded for clientId=" + id);
            }
        } else {
            logger.info("No entry exist in Redis; Creating new entry for clientId=" + id);
            hashOperations.put(id, "h", "0");
            redisTemplate.expire(id, 60, TimeUnit.SECONDS);
        }
    }

    /*@Override
    public void checkLimit(String id) {
        RedisUserEntry redisUserEntry = valueOperations.get(id);
        if(redisUserEntry != null) {
            boolean result = incrementVisitCount(redisUserEntry);
            if(result) {
                logger.info("RateLimit not exceeded for clientId=" + id);
               valueOperations.set(id, redisUserEntry);

                //redisKVTemplate.update(partialUpdate);
            }
            else {
                logger.info("RateLimit exceeded for clientId="+id);
                throw new LimitExceededException("RateLimit exceeded for clientId="+id);
            }
        } else {
            logger.info("No entry exist in Redis; Creating new entry for clientId="+id);
            valueOperations.set(id, new RedisUserEntry(id), 60, TimeUnit.SECONDS);
        }*/

}
