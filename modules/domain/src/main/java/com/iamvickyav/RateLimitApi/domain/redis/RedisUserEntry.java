package com.iamvickyav.RateLimitApi.domain.redis;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class RedisUserEntry implements Serializable {
    private String key;
    private AtomicInteger visitCount;
    private Integer maxLimit;

    public RedisUserEntry(String key) {
        this.key = key;
        this.maxLimit = 10;
        this.visitCount = new AtomicInteger(0);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AtomicInteger getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(AtomicInteger visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public void incrementVisitCount(){
      visitCount.incrementAndGet();
    }
}
