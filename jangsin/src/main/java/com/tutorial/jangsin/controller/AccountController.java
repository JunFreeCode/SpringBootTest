package com.tutorial.jangsin.controller;

import com.tutorial.jangsin.dto.Admin;
import com.tutorial.jangsin.mapper.AdminMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {
    private AdminMapper mapper;

    public AccountController(AdminMapper mapper){
        this.mapper = mapper;
    }

    //관리자 추가 폼
    @GetMapping("/login")
    public String loginForm(){
        return "account/login";
    }

    @PostMapping("/login")
    public String login(Member member, HttpSession session, Model model){
        //입력된 아이디, 비밀번호
        System.out.println(member.toString()+"<--입력된 정보");

        return "redirect:/";

    }
}
