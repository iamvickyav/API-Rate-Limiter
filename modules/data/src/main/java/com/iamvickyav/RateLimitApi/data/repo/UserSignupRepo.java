package com.iamvickyav.RateLimitApi.data.repo;

import com.iamvickyav.RateLimitApi.domain.entity.SignupInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSignupRepo extends JpaRepository<SignupInvite, Integer> {
    SignupInvite findByUserEmailAndInvitationCode(String userEmail, String invitationCode);
    SignupInvite findByUserEmail(String userEmail);
}
