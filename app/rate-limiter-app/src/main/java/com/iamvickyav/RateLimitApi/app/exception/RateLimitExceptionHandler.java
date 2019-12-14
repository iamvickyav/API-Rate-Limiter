package com.iamvickyav.RateLimitApi.app.exception;

import com.iamvickyav.RateLimitApi.domain.LimitExceededException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RateLimitExceptionHandler {

    @ExceptionHandler(value = LimitExceededException.class)
    ResponseEntity handleLimitExceededException(){
        Map<String, String> map = new HashMap();
        map.put("QUOTE_EXCEEDED", "USER QUOTA FOR THE HOUE EXHAUSTED");
        return new ResponseEntity<>(map, HttpStatus.TOO_MANY_REQUESTS);
    }
}
