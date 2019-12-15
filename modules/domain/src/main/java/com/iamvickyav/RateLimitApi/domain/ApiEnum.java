package com.iamvickyav.RateLimitApi.domain;

public enum ApiEnum {
    ALL_COUNTRY_LIST("getCountriesList", 1),
    COUNTRY_DATA_BY_CODE("getCountryByCode", 2);

    String apiName;
    Integer apiCode;

    ApiEnum(String apiName, Integer apiCode) {
        this.apiName = apiName;
        this.apiCode = apiCode;
    }

    public static Integer getApiCodeByName(String apiName) {
        Integer apiCode = 0;
        for (ApiEnum d : ApiEnum.values()) {
            if(d.apiName.equalsIgnoreCase(apiName))
                apiCode = d.apiCode;
        }
        return apiCode;
    }
}
