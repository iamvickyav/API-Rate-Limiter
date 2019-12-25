package com.iamvickyav.RateLimitApi.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_api_quota")
public class UserApiQuota {

    @EmbeddedId
    private UserQuotaId id;

    @Column(name = "quota_assigned")
    private Integer quotaAssigned;

    public UserApiQuota(){

    }

    public UserApiQuota(Integer apiId, Integer userId, Integer quotaAssigned) {
        this.id = new UserQuotaId(userId, apiId);
        this.quotaAssigned = quotaAssigned;
    }

    public UserQuotaId getId() {
        return id;
    }

    public void setId(UserQuotaId id) {
        this.id = id;
    }

    public Integer getQuotaAssigned() {
        return quotaAssigned;
    }

    public void setQuotaAssigned(Integer quotaAssigned) {
        this.quotaAssigned = quotaAssigned;
    }
}
