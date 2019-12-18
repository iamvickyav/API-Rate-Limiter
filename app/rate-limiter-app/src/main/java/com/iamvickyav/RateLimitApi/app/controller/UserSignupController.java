package com.iamvickyav.RateLimitApi.app.controller;

import com.iamvickyav.RateLimitApi.app.service.UserSignupService;
import com.iamvickyav.RateLimitApi.domain.request.SignupRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserSignupController {

    private static final Logger logger = LogManager.getLogger(UserSignupController.class);

    @Autowired
    UserSignupService signupService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    Map processRegistrationRequest(@RequestParam("emailId") String emailId) {
        signupService.processRegistration(emailId);
        Map<String, String> map = new HashMap<>();
        map.put("message" , "Thanks for your interest ! Please check your email for invitation code");
        return map;
    }

    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    ResponseEntity signupNewUser(@RequestBody @Valid SignupRequest request) {
        signupService.processSignupRequest(request);
        Map<String, String> map = new HashMap<>();
        map.put("message" , "user created successfully");
        logger.info("User Signup successful for userName="+request.getUserName());
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
