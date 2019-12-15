package com.iamvickyav.RateLimitApi.app.interceptor;

import com.iamvicky.RateLimitApi.redis.repo.RedisRepoImpl;
import com.iamvickyav.RateLimitApi.data.repo.UserApiQuotaRepo;
import com.iamvickyav.RateLimitApi.domain.entity.UserApiQuota;
import com.iamvickyav.RateLimitApi.domain.entity.UserQuotaId;
import com.iamvickyav.RateLimitApi.domain.exception.UserNotAuthorisedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private static final Logger logger = LogManager.getLogger(RateLimitInterceptor.class);

    @Autowired
    RedisRepoImpl redisRepoImpl;

    @Autowired
    UserApiQuotaRepo userApiQuotaRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("RateLimitInterceptor called");
        String clientId = request.getHeader("CLIENT_ID");
        checkIfLimitExceed(clientId, ((HandlerMethod) handler).getMethod().getName());
        return true;
    }

    private void checkIfLimitExceed(String clientId, String apiName) {
        Optional<UserApiQuota> userApiQuota = userApiQuotaRepo.findById(new UserQuotaId(clientId, apiName));
        if(userApiQuota.isPresent()){
            String redisKey = clientId + "-" + apiName;
            redisRepoImpl.checkLimit(redisKey, userApiQuota.get().quotaAssigned);
        } else {
            throw new UserNotAuthorisedException("User has no access to the API="+ apiName);
        }
    }
}
