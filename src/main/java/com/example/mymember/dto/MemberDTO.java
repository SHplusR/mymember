package com.example.mymember.dto;

import lombok.*;

@Getter
@Setter
@ToString //dto를 출력할때 사용한다.
@NoArgsConstructor //기본생성자를 자동으로 만들어준다
//@AllArgsConstructor // 이 필드를 모두 매개변수로 하는 생성자를 만들어준다.
public class MemberDTO {
    //dto 규칙 1. 해당 클래스에 작성하는 모든 필드는 private로.
    private Long id; //save.html에 있는 요소의 name과 이름이 같다면 spring이 알아서 담아줌
    private String memberEmail;
    private String memberPassword;
    private String memberName;
}
