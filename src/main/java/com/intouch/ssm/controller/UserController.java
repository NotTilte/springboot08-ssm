package com.intouch.ssm.controller;


import com.intouch.ssm.domain.User;
import com.intouch.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //显示登陆页面
    @GetMapping("/form")
    public  String showForm(Model model){
        model.addAttribute("user",new User());
        return "LoginForm";
    }

    //验证码校验
    @RequestMapping(value = "/verify")
    @ResponseBody
    public boolean verfyCode(@RequestParam("code") String validCode,
                             HttpServletRequest request, HttpServletResponse response)
                           throws IOException {
        System.out.println("validCode="+validCode);

        HttpSession session=request.getSession();
        String backCode= (String) session.getAttribute("backCode");

        boolean verifyOk=false;
        System.out.println("backCode="+backCode);
        if(backCode.equals(validCode)){
            verifyOk = true;
        }
        return verifyOk;
    }


    //定义处理登录的方法
    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                              Model model, HttpServletRequest request){


        if(bindingResult.hasErrors()){
            return "LoginForm";
        }
            //邮箱是否正确，密码是否正确
            User vaildUser=userService.login(user.getEmail());
            if (vaildUser == null||(!vaildUser.getPassword().equals(user.getPassword()))) {
                model.addAttribute("errorMessage","用户邮箱或密码填写错误");
                return "LoginForm";
            }

            //针对五合法登陆的用户我们需要记录登陆的最新时间和ip地址
            vaildUser.setLastLoginTime(System.currentTimeMillis());
            vaildUser.setLastLonginIp(request.getRemoteAddr());

            //同步到数据库中
            userService.modfyInfo(vaildUser);

            //存入到session域中
            HttpSession session=request.getSession();
            session.setAttribute("validUser",vaildUser);
        return "UserInfo";
    }

    @GetMapping("/logout")
    public String handleLogout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute("validUser");
        session.invalidate();
        return "redirect:/user/form";
    }
}
