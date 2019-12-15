package com.iamvicky.RateLimitApi.redis.repo;

public interface RedisRepo {
    void checkLimit(String id, Integer maxLimit);
}
