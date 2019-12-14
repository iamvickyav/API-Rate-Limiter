package com.iamvickyav.RateLimitApi.domain;

public class LimitExceededException extends RuntimeException {

    public LimitExceededException(String message) {
        super(message);
    }
}
