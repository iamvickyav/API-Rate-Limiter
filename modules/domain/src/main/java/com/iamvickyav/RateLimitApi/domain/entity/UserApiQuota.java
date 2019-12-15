package com.iamvickyav.RateLimitApi.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_api_quota")
public class UserApiQuota {

    @EmbeddedId
    public UserQuotaId id;

    @Column(name = "quota_assigned")
    public Integer quotaAssigned;
}
