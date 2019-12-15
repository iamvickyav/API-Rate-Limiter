package com.iamvickyav.RateLimitApi.data.repo;

import com.iamvickyav.RateLimitApi.domain.entity.UserApiQuota;
import com.iamvickyav.RateLimitApi.domain.entity.UserQuotaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApiQuotaRepo extends JpaRepository<UserApiQuota, UserQuotaId> {
}
