package com.iamvickyav.RateLimitApi.domain.entity;

import com.iamvickyav.RateLimitApi.domain.enums.Quota;

import javax.persistence.*;

@Entity
@Table(name = "api")
public class Api {

    @Id
    @Column(name = "api_id")
    private Integer apiId;

    @Column(name = "api_name")
    private String apiName;

    @Column(name = "default_max_quota")
    private Integer defaultQuota;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "quota_window")
    private Quota quotaWindow;

    public Quota getQuotaWindow() {
        return quotaWindow;
    }

    public void setQuotaWindow(Quota quotaWindow) {
        this.quotaWindow = quotaWindow;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Integer getDefaultQuota() {
        return defaultQuota;
    }

    public void setDefaultQuota(Integer defaultQuota) {
        this.defaultQuota = defaultQuota;
    }
}
