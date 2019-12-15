package com.iamvickyav.RateLimitApi.data.repo;

import com.iamvickyav.RateLimitApi.domain.entity.User;
import com.iamvickyav.RateLimitApi.domain.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
     UserPassword findByUserId(String userId);
}
