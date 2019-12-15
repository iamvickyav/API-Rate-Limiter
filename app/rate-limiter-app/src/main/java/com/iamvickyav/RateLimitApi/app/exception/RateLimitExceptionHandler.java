package com.iamvickyav.RateLimitApi.app.exception;

import com.iamvickyav.RateLimitApi.domain.exception.InvalidUserException;
import com.iamvickyav.RateLimitApi.domain.exception.LimitExceededException;
import com.iamvickyav.RateLimitApi.domain.exception.UserNotAuthorisedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RateLimitExceptionHandler {

    @ExceptionHandler(value = LimitExceededException.class)
    ResponseEntity handleLimitExceededException(LimitExceededException ex){
        return generateResponseEntity("QUOTE_EXCEEDED", ex.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(value = UserNotAuthorisedException.class)
    ResponseEntity handleUserNotAuthorisedException(UserNotAuthorisedException ex){
        return generateResponseEntity("NO_ACCESS_TO_API", ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = InvalidUserException.class)
    ResponseEntity handleInvalidUserException(InvalidUserException ex) {
        return generateResponseEntity("INVALID_USER", ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity generateResponseEntity(String code, String message, HttpStatus httpStatus) {
        Map<String, String> map = new HashMap<>();
        map.put("CODE", code);
        map.put("MESSAGE", message);
        return new ResponseEntity<>(map, httpStatus);
    }
}
