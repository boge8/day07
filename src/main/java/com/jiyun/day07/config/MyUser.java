package com.jiyun.day07.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MyUser implements UserDetails {
    public MyUser(String username, String password, ArrayList<GrantedAuthority> authList, String nickname, String phonenum) {

        this.username = username;
        this.password = password;
        this.authList = authList;
        this.nickname = nickname;
        this.phonenum = phonenum;
    }

    private String username;
    private String password;
    private ArrayList<GrantedAuthority> authList;
    private String nickname;
    private String phonenum;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<GrantedAuthority> getAuthList() {
        return authList;
    }

    public void setAuthList(ArrayList<GrantedAuthority> authList) {
        this.authList = authList;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authList=" + authList +
                ", nickname='" + nickname + '\'' +
                ", phonenum='" + phonenum + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
