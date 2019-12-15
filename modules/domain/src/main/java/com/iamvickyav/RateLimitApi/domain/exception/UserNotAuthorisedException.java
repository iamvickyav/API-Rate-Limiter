package com.iamvickyav.RateLimitApi.domain.exception;

public class UserNotAuthorisedException extends RuntimeException {

    public UserNotAuthorisedException(String message){
        super(message);
    }
}
