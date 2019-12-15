create table user (
	user_id int PRIMARY KEY AUTO_INCREMENT,
	user_email varchar(50) NOT NULL,
    user_enc_password varchar(50) NOT NULL
);

create table api (
	api_id int PRIMARY KEY AUTO_INCREMENT,
	api_name varchar(20) NOT NULL,
    default_quota int NOT NULL
);

create table user_api_quota (
	user_id int,
    api_id int,
    quota_assigned int,
    PRIMARY KEY (user_id, api_id)
);

create table country (
	country_id int PRIMARY KEY AUTO_INCREMENT,
    country_name VARCHAR(40) NOT NULL,
    country_code VARCHAR(3) NOT NULL UNIQUE,
    UN_member VARCHAR(1) NOT NULL,
    UN_member_since int
);