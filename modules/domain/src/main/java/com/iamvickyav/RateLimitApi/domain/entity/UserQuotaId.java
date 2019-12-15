package com.iamvickyav.RateLimitApi.domain.entity;

import com.iamvickyav.RateLimitApi.domain.ApiEnum;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserQuotaId implements Serializable {

    @Column(name = "user_id")
    public String userId;

    @Column(name = "api_id")
    public Integer apiId;

    public UserQuotaId(){
    }

    public UserQuotaId(String userId, Integer apiId) {
        this.userId = userId;
        this.apiId = apiId;
    }

    public UserQuotaId(String userId, String apiName) {
        this.userId = userId;
        this.apiId =  ApiEnum.getApiCodeByName(apiName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuotaId that = (UserQuotaId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(apiId, that.apiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, apiId);
    }
}
