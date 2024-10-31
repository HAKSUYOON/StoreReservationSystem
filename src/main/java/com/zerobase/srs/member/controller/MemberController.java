package com.zerobase.srs.member.controller;

import com.zerobase.srs.member.model.MemberInput;
import com.zerobase.srs.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인
     */
    @RequestMapping("/member/login")
    public String login() {

        return "member/login";
    }

    /**
     * 회원가입 GET
     */
    @GetMapping("/member/register")
    public String register() {

        return "member/register";
    }

    /**
     * 회원가입 POST
     */
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request
            , MemberInput parameter) {

        boolean result = memberService.register(parameter);
        model.addAttribute("result", result);

        return "member/register_complete";
    }

}
