package com.iamvickyav.RateLimitApi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    String userId;

    @Column(name = "user_email")
    String userEmail;

    @Column(name = "user_enc_password")
    String userPassword;
}
