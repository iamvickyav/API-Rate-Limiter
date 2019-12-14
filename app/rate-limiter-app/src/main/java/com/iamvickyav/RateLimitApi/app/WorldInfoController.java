package com.iamvickyav.RateLimitApi.app;

import com.iamvickyav.RateLimitApi.app.interceptor.RateLimitInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WorldInfoController {

    private static final Logger logger = LogManager.getLogger(WorldInfoController.class);

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    Map testing(){
        logger.info("Hello From Controller");
        Map<String, String> map = new HashMap();
        map.put("message", "test successful");
        return map;
    }
}
