package com.iamvickyav.RateLimitApi.data.repo;

import com.iamvickyav.RateLimitApi.domain.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepo extends JpaRepository<Api, Integer> {
}
