package com.iamvickyav.RateLimitApi.app.controller;

import com.iamvickyav.RateLimitApi.data.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HeartbeatController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
    Map checkHeartBeat(){
        Map<String, String> map = new HashMap<>();
        map.put("message", "Application is running");
        return map;
    }

    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
    Map checkHealthCheck(){
        Map<String, String> map = new HashMap<>();
        Integer dbResponse = userRepo.checkDBConnectionStatus();
        String dbStatus = (dbResponse!=null) ? "Up and running" : "Down";
        map.put("Database" , dbStatus);
        String redisResponse = redisTemplate.getConnectionFactory().getConnection().ping();
        String redisStatus = (redisResponse!=null) ? "Up and running" : "Down";
        map.put("Redis", redisStatus);
        return map;
    }
}