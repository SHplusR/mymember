package com.example.mymember.service;

import com.example.mymember.dto.MemberDTO;
import com.example.mymember.entity.MemberEntity;
import com.example.mymember.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public MemberDTO login(MemberDTO memberDTO) {
        /*1. 회원이 입력한 이메일로 db에서 조회를 함.
        2. 해당 이메일의 비밀번호와 db상의 비밀번호가 일치하는지 확인
         */
        Optional<MemberEntity> byMemberEntity = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEntity.isPresent()){
            //db에 해당 이메일 정보가 존재한다면
            MemberEntity memberEntity = byMemberEntity.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                System.out.println("Entitypwd : "+memberEntity.getMemberPassword());
                System.out.println("dtoypwd : "+memberEntity.getMemberPassword());
                //비밀번호 동일
               MemberDTO member = MemberDTO.toMemberDTO(memberEntity);
               return member;
            }
            else{
                return null;
            }
        }
        else{
            //정보가 없다면
            return null;
        }

    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity memberEntity : memberEntityList){
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
       Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
       if(optionalMemberEntity.isPresent()){
           return MemberDTO.toMemberDTO(optionalMemberEntity.get());
       }
       else{
           return null;
       }
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> opmemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(opmemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(opmemberEntity.get());
        }
        else{
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        //repo의 save는 db에 아이디가 있으면 업데이트, 없으면 insert를 해줌.
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);

    }
}
