package com.zerobase.srs.member.service;

import com.zerobase.srs.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    /**
     * 회원가입
     */
    boolean register(MemberInput parameter);
}
