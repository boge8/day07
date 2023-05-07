package com.jiyun.day07.controller;

import com.jiyun.day07.config.MyUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login.html")
    public String toLogin(){
        return "login";

    }
    @ResponseBody
    @GetMapping("/logoutSuccess.html")
    public String logoutSuccess(){
        return "退出成功！";

    }
    @ResponseBody
    @GetMapping("/error.html")
    public String error(){
        return "登录错误！";

    }
@GetMapping("/main.html")
public String main(HttpSession session){
        //获取用户信息，存放到session域中
        SecurityContextImpl securityContextImpl =  (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication token = securityContextImpl.getAuthentication();
    MyUser userDetails = (MyUser)token.getPrincipal();//将主体转为用户详情对象
        String username = userDetails.getUsername();
      System.out.println(userDetails);
        session.setAttribute("username",username);
        return "main";
    }

}
