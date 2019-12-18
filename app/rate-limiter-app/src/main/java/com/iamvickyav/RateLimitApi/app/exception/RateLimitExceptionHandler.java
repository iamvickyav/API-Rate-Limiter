package com.iamvickyav.RateLimitApi.app.exception;

import com.iamvickyav.RateLimitApi.app.controller.UserSignupController;
import com.iamvickyav.RateLimitApi.domain.exception.InvalidUserException;
import com.iamvickyav.RateLimitApi.domain.exception.LimitExceededException;
import com.iamvickyav.RateLimitApi.domain.exception.UnsupportedApiException;
import com.iamvickyav.RateLimitApi.domain.exception.UserNotAuthorisedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RateLimitExceptionHandler {

    private static final Logger logger = LogManager.getLogger(RateLimitExceptionHandler.class);

    @ExceptionHandler(value = LimitExceededException.class)
    ResponseEntity handleLimitExceededException(LimitExceededException ex){
        logger.error("QUOTE_EXCEEDED exception");
        return generateResponseEntity("QUOTE_EXCEEDED", ex.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(value = UserNotAuthorisedException.class)
    ResponseEntity handleUserNotAuthorisedException(UserNotAuthorisedException ex){
        logger.error("NO_ACCESS_TO_API exception");
        return generateResponseEntity("NO_ACCESS_TO_API", ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = InvalidUserException.class)
    ResponseEntity handleInvalidUserException(InvalidUserException ex) {
        logger.error("INVALID_USER exception");
        return generateResponseEntity("INVALID_USER", ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = JDBCConnectionException.class)
    ResponseEntity handleJDBCConnectionException() {
        logger.error("DB_CONN_ISSUE exception");
        return generateResponseEntity("DB_CONN_ISSUE", "DB is down", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UnsupportedApiException.class)
    ResponseEntity handleUnsupportedApiException(UnsupportedApiException ex) {
        logger.error("UNSUPPORTED_API exception");
        return generateResponseEntity("UNSUPPORTED_API", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("INVALID_INPUT exception");
        return generateResponseEntity("INVALID_INPUT", "input is not valid", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity handleGenericException(Exception ex) {
        logger.error("SYSTEM_ISSUE exception");
        return generateResponseEntity("SYSTEM_ISSUE", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity generateResponseEntity(String code, String message, HttpStatus httpStatus) {
        Map<String, String> map = new HashMap<>();
        map.put("CODE", code);
        map.put("MESSAGE", message);
        return new ResponseEntity<>(map, httpStatus);
    }
}
