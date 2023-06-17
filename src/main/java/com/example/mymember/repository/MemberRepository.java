package com.example.mymember.repository;

import com.example.mymember.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
//이메일로 회원정보를 조회하는 기능.
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
