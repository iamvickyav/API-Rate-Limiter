package com.iamvickyav.RateLimitApi.app.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;

import java.util.UUID;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        prepareMDC(request);
        logger.info("Incoming Request");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long currentTime = System.currentTimeMillis();

        long incomingTime = Long.parseLong(MDC.get("incoming_time"));
        logger.info("Request Completed; timeTaken="+(currentTime-incomingTime));
        MDC.clear();
    }

    private void prepareMDC(HttpServletRequest request){
        long currentTime = System.currentTimeMillis();
        MDC.put("incoming_time", Long.toString(currentTime));
        MDC.put("request_id" , UUID.randomUUID().toString());
        MDC.put("uri", request.getRequestURI());
    }
}
