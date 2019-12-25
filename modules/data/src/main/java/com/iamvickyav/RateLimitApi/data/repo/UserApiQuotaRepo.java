package com.iamvickyav.RateLimitApi.data.repo;

import com.iamvickyav.RateLimitApi.domain.entity.UserApiQuota;
import com.iamvickyav.RateLimitApi.domain.entity.UserQuotaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserApiQuotaRepo extends JpaRepository<UserApiQuota, UserQuotaId> {

    @Modifying
    @Query("update UserApiQuota u set u.quotaAssigned = ?3 where u.id.userId = ?2 and u.id.apiId = ?1")
    public void updateUserQuota(Integer apiId, Integer userId, Integer newQuota);
}
