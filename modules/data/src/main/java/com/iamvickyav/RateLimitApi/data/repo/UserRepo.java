package com.iamvickyav.RateLimitApi.data.repo;

import com.iamvickyav.RateLimitApi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {

     @Query(value = "SELECT 1 FROM DUAL" , nativeQuery = true)
     Integer checkDBConnectionStatus();

    // UserPassword findByUserName(String userName);

     User findByUserName(String userName);
}
