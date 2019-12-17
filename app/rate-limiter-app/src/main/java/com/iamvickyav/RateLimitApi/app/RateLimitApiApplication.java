package com.iamvickyav.RateLimitApi.app;

import com.iamvicky.RateLimitApi.redis.config.RedisConfig;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Import(RedisConfig.class)
@ComponentScan(basePackages =
		{"com.iamvickyav.RateLimitApi.data",
		"com.iamvickyav.RateLimitApi.app",
		"com.iamvicky.RateLimitApi.redis.repo"})
@EntityScan(basePackages = "com.iamvickyav.RateLimitApi.domain.entity")
@EnableJpaRepositories(basePackages = "com.iamvickyav.RateLimitApi.data.repo")
@EnableCaching
@SpringBootApplication
public class RateLimitApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RateLimitApiApplication.class, args);
	}

	@Bean
	PasswordEncryptor passwordEncryptor(){
		return new StrongPasswordEncryptor();
	}

	@Bean
	CacheManager concurrentMapCacheManager(){
		return new ConcurrentMapCacheManager();
	}
}
