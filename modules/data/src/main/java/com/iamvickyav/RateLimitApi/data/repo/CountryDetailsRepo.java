package com.iamvickyav.RateLimitApi.data.repo;

import com.iamvickyav.RateLimitApi.domain.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryDetailsRepo extends JpaRepository<Country, Integer> {
    Country findByCountryCode(String countryCode);
    List<Country> findByIsMemberOfUN(Boolean status);
}
