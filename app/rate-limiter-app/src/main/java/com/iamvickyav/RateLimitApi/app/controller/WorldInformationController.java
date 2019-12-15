package com.iamvickyav.RateLimitApi.app.controller;

import com.iamvickyav.RateLimitApi.data.repo.CountryDetailsRepo;
import com.iamvickyav.RateLimitApi.domain.entity.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorldInformationController {

    private static final Logger logger = LogManager.getLogger(WorldInformationController.class);

    @Autowired
    CountryDetailsRepo countryDetailsRepo;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    List<Country> getCountriesList(@RequestParam(required = false) Boolean memberOfUN) {
        List<Country> countryList;
        if(memberOfUN == null)
            countryList = countryDetailsRepo.findAll();
        else
            countryList = countryDetailsRepo.findByIsMemberOfUN(memberOfUN);
        return countryList;
    }

    @RequestMapping(value = "/country/{code}", method = RequestMethod.GET)
    ResponseEntity<Country> getCountryByCode(@PathVariable("code") String countryCode) {
        Country country = countryDetailsRepo.findByCountryCode(countryCode);
        ResponseEntity<Country> response;
        if(country != null)
            response =  new ResponseEntity<>(country, HttpStatus.OK);
        else
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }
}
