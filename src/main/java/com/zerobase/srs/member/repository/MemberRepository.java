package com.zerobase.srs.member.repository;

import com.zerobase.srs.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
