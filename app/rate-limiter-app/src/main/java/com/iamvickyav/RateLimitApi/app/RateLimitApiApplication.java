package com.iamvickyav.RateLimitApi.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import com.iamvicky.RateLimitApi.redis.config.RedisConfig;

@Import(RedisConfig.class)
@ComponentScan(basePackages =
		{"com.iamvickyav.RateLimitApi.data",
		"com.iamvickyav.RateLimitApi.app",
		"com.iamvicky.RateLimitApi.redis.repo"})
@SpringBootApplication
public class RateLimitApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateLimitApiApplication.class, args);
	}
}
