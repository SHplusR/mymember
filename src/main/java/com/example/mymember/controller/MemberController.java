package com.example.mymember.controller;

import com.example.mymember.dto.MemberDTO;

import com.example.mymember.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        System.out.println("login");
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null){
            //login success
            session.setAttribute("loginEmail",loginResult.getMemberEmail());
            return "main";
        }
        else{
            return "login";
        }
    }

    //멤버 리스트
    @GetMapping("/")
    public String findAll(Model model){
        //모든 멤버 목록 가져오기.(하나가 아니므로 list 사용)
        //model : 객체의 정보를 화면에 뿌릴떄 model에 넣어서 뿌린다.
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList",memberDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    //@PathVariable -> 위 경로상에 있는 값을 가져올때
    //이 아이디에 해당하는 정보를 db 에서 가져와서 화면에 출력해줘야하므로 model 이 필요
    public String findById(@PathVariable Long id, Model model){
        //하나만 필요하니까 memberdto
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "detail";
    }

    @GetMapping("/update")
    public String updateForm(HttpSession httpSession, Model model){
       String myEmail = (String)httpSession.getAttribute("loginEmail");
       MemberDTO memberDTO = memberService.updateForm(myEmail);
       model.addAttribute("updateMember",memberDTO);
       return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
}
