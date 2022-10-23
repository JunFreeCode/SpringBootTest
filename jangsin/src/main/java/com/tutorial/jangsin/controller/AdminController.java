package com.tutorial.jangsin.controller;

import com.tutorial.jangsin.dto.Admin;
import com.tutorial.jangsin.mapper.AdminMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 특정  URL을 요청하면 동작하는 method를 정의하는 부분이다.
 *  @Controller 어노테이션을 사용해야 하며, 호출될 method 위에는 @GetMapping 어노테이션을 이용해서 Url을 지정한다.
 *  그리고, 앞에서 만들어 놓은 AdminMapper를 통해서 DB에 접근할 수 있다.
 *  Mapper 와 Controller 사이에 service 레이어를 추가하는 경우가 많은데, 특별한 목적이 있지 않으면 복잡도만 증가시킨다고 판단하여 작성하지 않았다.
 */
@Controller
@RequestMapping("/mgmt")
public class AdminController {
    private AdminMapper mapper;

    public AdminController(AdminMapper mapper){
        this.mapper = mapper;
    }

    //관리자 목록
    @GetMapping("/admin")
    public String list(Model model){
        List<Admin> admins= mapper.listAdmin();
        model.addAttribute("admins",admins);
        return "mgmt/admin/list";
    }

    //관리자 추가 폼
    @GetMapping("/admin/insert_form")
    public String insertForm(){
        return "mgmt/admin/insert_form";
    }

    //관리자 추가
    @PostMapping("/admin/insert")
    public String insert(@RequestParam("userid") String userid, @RequestParam("passwd1") String passwd1,@RequestParam("passwd2") String passwd2, @RequestParam("nick") String nick){
        String password;
        if(!passwd1.equals(passwd2)){
            //비밀번호가 다른 경우, 관리자 목록으로 돌아간다.
            return "redirect:/mgmt/admin";
        }

        password = passwd1;
        mapper.insertAdmin(userid,password,nick);
        //관리자 추가 성공
        return "redirect:/mgmt/admin";
    }

    //관리자 비밀번호 변경 폼
    //URL에 포함된 변수값(id)을 받기 위해서 @PathVariable 어노테이션을 이용하였다.
    @GetMapping("/admin/chg_passwd_form/{id}")
    public String chgPasswdForm(@PathVariable("id") Long id, Model model){
        Admin admin = mapper.getAdmin(id);
        model.addAttribute("admin",admin);
        return "mgmt/admin/chg_passwd_form";
    }

    //관리자 비밀번호 수정
    @PostMapping("/admin/chg_passwd")
    public String chg_passwd(@RequestParam("id") Long id, @RequestParam("passwd1") String passwd1, @RequestParam("passwd2") String passwd2){
        if(!passwd1.equals(passwd2)){
            return "redirect:/mgmt/admin";
        }
        mapper.updateAdminPassword(id, passwd1);
        return "redirect:/mgmt/admin";
    }

    //관리자 삭제
    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        mapper.deleteAdmin(id);
        return "redirect:/mgmt/admin";
    }

    //관리자 수정 폼
    @GetMapping("/admin/update_form/{id}")
    public String updateForm(@PathVariable("id")Long id, Model model){
        Admin admin = mapper.getAdmin(id);
        model.addAttribute("admin",admin);
        return "mgmt/admin/update_form";
    }

    //관리자 수정
    @PostMapping("/admin/update")
    public String update(@RequestParam("id") Long id, @RequestParam("nick") String nick){
        mapper.updateAdmin(id, nick);
        return "redirect:/mgmt/admin";
    }

}
