package com.iamvickyav.RateLimitApi.app.service;

import com.iamvickyav.RateLimitApi.data.repo.CountryDetailsRepo;
import com.iamvickyav.RateLimitApi.domain.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldInfoService {

    @Autowired
    CountryDetailsRepo countryDetailsRepo;

    @Cacheable(cacheNames = "all-countries-info", cacheManager = "concurrentMapCacheManager")
    public List<Country> getAllCountryInformation(Boolean memberOfUN){
        List<Country> countryList;
        if(memberOfUN == null)
            countryList = countryDetailsRepo.findAll();
        else
            countryList = countryDetailsRepo.findByIsMemberOfUN(memberOfUN);
        return countryList;
    }

    @Cacheable(cacheNames = "country-info", cacheManager = "concurrentMapCacheManager")
    public Country getCountryByCode(String countryCode) {
        return countryDetailsRepo.findByCountryCode(countryCode);
    }
}
