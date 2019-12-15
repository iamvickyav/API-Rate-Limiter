package com.iamvickyav.RateLimitApi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api")
public class Api {

    @Id
    @Column(name = "api_id")
    Integer apiId;

    @Column(name = "api_name")
    String apiName;

    @Column(name = "default_quota")
    Integer defaultQuota;
}
