create database MY_DB;
use MY_DB;

create table user (
	user_id int PRIMARY KEY AUTO_INCREMENT,
    user_name varchar(20) NOT NULL UNIQUE,
	user_email varchar(50) NOT NULL,
    user_enc_password varchar(100) NOT NULL
);

create table api (
	api_id int PRIMARY KEY AUTO_INCREMENT,
	api_name varchar(20) NOT NULL,
    default_max_quota int NOT NULL,
    quota_window int NOT NULL
);

create table user_api_quota (
	user_id int,
    api_id int,
    quota_assigned int,
    PRIMARY KEY (user_id, api_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (api_id) REFERENCES api(api_id)
);

create table country (
	country_id int PRIMARY KEY AUTO_INCREMENT,
    country_name VARCHAR(40) NOT NULL,
    country_code VARCHAR(3) NOT NULL UNIQUE,
    UN_member VARCHAR(1) NOT NULL,
    UN_member_since int
);

create table signup_invitation (
	invite_id int PRIMARY KEY AUTO_INCREMENT,
	user_email varchar(100) NOT NULL UNIQUE,
    invitation_code varchar(10) NOT NULL
);