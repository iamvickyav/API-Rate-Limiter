package com.iamvickyav.RateLimitApi.domain.entity;

import com.iamvickyav.RateLimitApi.domain.ApiEnum;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserQuotaId implements Serializable {

    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "api_id")
    public Integer apiId;

    public UserQuotaId(){

    }

    public UserQuotaId(Integer userId, Integer apiId) {
        this.userId = userId;
        this.apiId = apiId;
    }

    public UserQuotaId(String userId, String apiName) {
        this.userId = Integer.valueOf(userId);
        this.apiId =  ApiEnum.getApiCodeByName(apiName);
    }


}
