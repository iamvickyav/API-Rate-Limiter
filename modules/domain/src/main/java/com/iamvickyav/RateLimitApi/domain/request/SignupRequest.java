package com.iamvickyav.RateLimitApi.domain.request;

import javax.validation.constraints.NotNull;

public class SignupRequest {

    @NotNull
    private String userName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String invitationCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
