package com.example.mymember.controller;

import com.example.mymember.dto.MemberDTO;

import com.example.mymember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor //service에 대한 의존성 주입. line13
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService; //final 필수야...
    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/save")
    public String Save(@ModelAttribute MemberDTO memberDTO){
        System.out.println(memberDTO);
        memberService.save(memberDTO);
        return "index";
    }
}
