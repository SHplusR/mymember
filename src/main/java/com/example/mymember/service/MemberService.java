package com.example.mymember.service;

import com.example.mymember.dto.MemberDTO;
import com.example.mymember.entity.MemberEntity;
import com.example.mymember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        //repo의 save 메소드를 호출. (조건 : entity를 넘겨줘야함.)
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        //아래 save는 원래 제공해주는거임! 이름 바꾸기 못함!
        memberRepository.save(memberEntity);
    }
}
