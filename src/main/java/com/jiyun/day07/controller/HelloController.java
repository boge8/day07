package com.jiyun.day07.controller;

import com.jiyun.day07.config.MyUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@RestController
public class HelloController {
    @GetMapping("/a/1")
    public String a1(){
        return "a1";
    }
    @GetMapping("/b/1")
    public String b1(){
        return "b1";
    }
    @GetMapping("/c/1")
    public String c1(){
        return "c1";
    }
    @GetMapping("/d/1")
    public String d1(){
        return "d1";
    }

    @RequestMapping("/h2")
    public String h2(){
        return "h2";
    }
    @RequestMapping("/h1")
    public String hi1(HttpSession httpSession) {
        //每一个用户登录成功，在Session域中存储着其对应的权限信息
        SecurityContextImpl securityContext =(SecurityContextImpl) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        //获取登录的用户权限相关信息
        Authentication authentication = securityContext.getAuthentication();
        //获取用户详情
        Object details = authentication.getDetails();
        //获取登录的账号
        Object principal = authentication.getPrincipal();
        // 获取登录的凭证
        Object credentials = authentication.getCredentials();
        // 获取登录的用户拥有的权限
        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();

        //获取用户信息，类型为User，包含用户详情信息
        MyUser user = (MyUser) authentication.getPrincipal();
        //获取登录的用户名
        String username = user.getUsername();
        //获取登录的用户密码
        String password = user.getPassword();
        //获取登录的用户拥有的权限
        Collection<? extends GrantedAuthority> authorities1 = user.getAuthorities();

        //获取用户的在状态，如：是否可用、账号是否没有过期、账号是否没有被锁定、密码是否没有过期
        boolean isEnabled =  user.isEnabled();
        boolean isAccountNonExpired =  user.isAccountNonExpired();
        boolean isAccountNonLocked =  user.isAccountNonLocked();
        boolean isCredentialsNonExpired =  user.isCredentialsNonExpired();
        return "hi1";
    }
}
