package com.jiyun.day07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> authList = new ArrayList<>();
        // 为用户分配角色
        SimpleGrantedAuthority insert = new SimpleGrantedAuthority("insert");
        SimpleGrantedAuthority delete = new SimpleGrantedAuthority("delete");
        SimpleGrantedAuthority update = new SimpleGrantedAuthority("update");
        SimpleGrantedAuthority select = new SimpleGrantedAuthority("select");
        // 为用户分配权限
        SimpleGrantedAuthority employee = new SimpleGrantedAuthority("ROLE_Employee");
        SimpleGrantedAuthority manager = new SimpleGrantedAuthority("ROLE_Manager");
        SimpleGrantedAuthority boss = new SimpleGrantedAuthority("ROLE_Boss");
        authList.addAll(Arrays.asList(insert,delete,update,select,employee,manager,boss));
        if(username.equals("dsj")){
        MyUser myUser = new MyUser("dsj", "$2a$10$XLPyVHiqzijcp87xB/htKOaBNMU2JnN8OsLEd0WEptwPCcCCYz44e", authList, "大数据", "18888888888");
        return myUser;
        }
        else{
          new RuntimeException("登录失败，密码错误！");
          return null;
        }
    }
}