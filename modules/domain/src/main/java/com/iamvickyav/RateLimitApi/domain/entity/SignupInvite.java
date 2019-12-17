package com.iamvickyav.RateLimitApi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "signup_invitation")
public class SignupInvite {

    @Id
    @Column(name = "invite_id")
    private Integer inviteId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "invitation_code")
    private String invitationCode;

    public Integer getInviteId() {
        return inviteId;
    }

    public void setInviteId(Integer inviteId) {
        this.inviteId = inviteId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
