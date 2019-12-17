package com.iamvickyav.RateLimitApi.domain.exception;

public class UnsupportedApiException extends RuntimeException {

    public UnsupportedApiException(String message) {
        super(message);
    }
}
