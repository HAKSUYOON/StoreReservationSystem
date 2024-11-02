package com.zerobase.srs.member.service;

import com.zerobase.srs.member.entity.Member;
import com.zerobase.srs.member.model.MemberInput;
import com.zerobase.srs.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        if (optionalMember.isPresent()) {
            return false;
        }

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

        Member member = Member.builder()
                              .userId(parameter.getUserId())
                              .password(encPassword)
                              .phone(parameter.getPhone())
                              .role(parameter.getRole())
                              .build();
        memberRepository.save(member);

        return true;
    }

    @Override
    public String getPhone(String partnerId) {

        Optional<Member> optionalMember = memberRepository.findById(partnerId);
        if (!optionalMember.isPresent()) {
            return null;
        }
        Member member = optionalMember.get();

        return member.getPhone();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(username);
        if (optionalMember.isEmpty()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (member.getRole().equals("ROLE_CUSTOMER")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }

        if (member.getRole().equals("ROLE_PARTNER")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_PARTNER"));
        }

        if (member.getRole().equals("ROLE_ADMIN")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
    }
}
