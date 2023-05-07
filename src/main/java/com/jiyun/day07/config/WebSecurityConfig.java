package com.jiyun.day07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailService myUserDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/img/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //父类中默认进行了配置，我们将其拿到子类，按照自己的需求进行修改
        http
                .authorizeRequests()//进行权限设置
                .mvcMatchers("/logoutSuccess.html")
                .permitAll()
                .antMatchers("/a/**")
                .hasRole("Boss")
                .antMatchers("/b/**")
                .hasRole("Aaa")
                .antMatchers("/c/**")
                .hasAuthority("Bbb")
                .antMatchers("/d/**")
                .hasAuthority("update")
                .anyRequest()//任何请求
                .authenticated()//进行认证
                .and()
                .formLogin()//设置表单登录，后续可以在这里修改自定义登录页面
                .loginPage("/login.html") //设置自定义的登录页面
                .loginProcessingUrl("/denglu") //指定处理登录请求的路径，对应form表单的action地址
                .permitAll()
                .usernameParameter("uname")  //设置登录表单中的账号参数的name，默认为username
                .passwordParameter("pwd") //设置登录表单中的密码参数的name，默认为password
                .failureUrl("/error.html") //指定权限认证失败跳转的错误页面
                .defaultSuccessUrl("/main.html", false) //直接访问登录页面，登录成功跳转本页面,否则登录成功跳回到登录前访问的地址
                .and()
                .logout()
                .logoutUrl("/logout") //退出登录路径
                .logoutSuccessUrl("/logoutSuccess.html")// 设置退出后跳转的路径
                .and().csrf().disable() //禁用csrf功能，这里暂时用不到
        ;
    }

}