package com.iamvickyav.RateLimitApi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "country")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Country implements Serializable {

    @Id
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code")
    private String countryCode;

    @Type(type="yes_no")
    @Column(name = "UN_member")
    private Boolean isMemberOfUN;

    @Column(name = "UN_member_since")
    private Integer UNMemberYear;

    @JsonIgnore
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @JsonProperty(value = "country_name")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty(value = "country_code")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonIgnore
    public Boolean getMemberOfUN() {
        return isMemberOfUN;
    }

    public void setMemberOfUN(Boolean memberOfUN) {
        isMemberOfUN = memberOfUN;
    }

    @JsonProperty(value = "UN_member_since")
    public Integer getUNMemberYear() {
        return UNMemberYear;
    }

    public void setUNMemberYear(Integer UNMemberYear) {
        this.UNMemberYear = UNMemberYear;
    }
}
