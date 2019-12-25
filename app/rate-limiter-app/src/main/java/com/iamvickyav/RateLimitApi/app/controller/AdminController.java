package com.iamvickyav.RateLimitApi.app.controller;

import com.iamvickyav.RateLimitApi.app.service.AdminService;
import com.iamvickyav.RateLimitApi.domain.request.UserQuotaUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/user/quota", method = RequestMethod.POST)
    ResponseEntity updateUserInfo(@RequestBody UserQuotaUpdateRequest request){
        adminService.processRequest(request);
        return new ResponseEntity(HttpStatus.OK);
    }
}