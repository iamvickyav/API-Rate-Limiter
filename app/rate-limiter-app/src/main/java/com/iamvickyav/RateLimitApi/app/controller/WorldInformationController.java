package com.iamvickyav.RateLimitApi.app.controller;

import com.iamvickyav.RateLimitApi.app.service.WorldInfoService;
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
    WorldInfoService infoService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    List<Country> getCountriesList(@RequestParam(required = false) Boolean memberOfUN) {
        logger.info("Call for getCountriesList");
        return infoService.getAllCountryInformation(memberOfUN);
    }

    @RequestMapping(value = "/country/{code}", method = RequestMethod.GET)
    ResponseEntity<Country> getCountryByCode(@PathVariable("code") String countryCode) {
        logger.info("Call for getCountryByCode");
        ResponseEntity<Country> response;
        Country country = infoService.getCountryByCode(countryCode);
        if(country != null)
            response =  new ResponseEntity<>(country, HttpStatus.OK);
        else
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }
}
