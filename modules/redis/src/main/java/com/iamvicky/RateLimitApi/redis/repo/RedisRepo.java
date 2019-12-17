package com.iamvicky.RateLimitApi.redis.repo;

public interface RedisRepo {
    String getUsage(String redisKey);
    void checkLimit(String redisKey, String userName, String usage);
}
