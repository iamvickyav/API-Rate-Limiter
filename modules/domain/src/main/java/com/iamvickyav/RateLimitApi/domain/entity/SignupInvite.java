package com.iamvickyav.RateLimitApi.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "signup_invitation")
public class SignupInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_id")
    private Integer inviteId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "invitation_code")
    private String invitationCode;

    SignupInvite() {
    }

    public SignupInvite(String userEmail, String invitationCode) {
        this.userEmail = userEmail;
        this.invitationCode = invitationCode;
    }

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
