package com.iamvickyav.RateLimitApi.app.controller;

import com.iamvickyav.RateLimitApi.data.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HeartbeatController {

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
    Map checkHeartBeat(){
        Map<String, String> map = new HashMap<>();
        map.put("message", "Application is running");
        return map;
    }

    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
    Map checkHealthCheck(){
        Map<String, String> map = new HashMap<>();
        Integer result = userRepo.checkDBConnectionStatus();
        if(result != null) {
            map.put("message", "DB is up and running");
        } else {
            map.put("message", "DB is down");
        }
        return map;
    }
}
