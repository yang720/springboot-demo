package com.ly.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author liuyang
 * @date 2020/3/13 18:29
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 授权规则
     * @param http 请求
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能，效果：如果没有登录，没有权限跳转到登录页面
        //spring自动完成：1、/login来到登陆页面   2、重定向到/login?error表示登陆失败   3、更多详细规定
        //http.formLogin();

        //自己定制登陆页面,controller中写好处理方法，首页修改请求地址@{/userlogin}
        //1、默认post形式的 /login代表处理登陆
        //2、一旦定制loginPage: 那么 loginPage的post请求就是登陆
        //3、user和 pwd 是自己login.html页面的输入框name值
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");


        //开启自动配置的注销功能
        //spring自动完成：1、访问 /logout 表示用户注销，清空session   2、注销成功会返回 /login?logout 页面
        //logoutSuccessUrl("/") 注销成功后来到首页
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能
        //spring自动完成：1、登陆成功后，将cookie保存到浏览器，默认时间内免登陆自动读取cookie信息   2、点击注销会删除cookie
        //http.rememberMe();
        //定制记住我
        http.rememberMe().rememberMeParameter("remember");
    }

    /**
     * 认证规则
     * @param auth 身份
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        //可以设置内存指定的登录的账号密码,指定角色
        //不加.passwordEncoder(new MyPasswordEncoder())
        //就不是以明文的方式进行匹配，会报错
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("VIP1","VIP2","VIP3");

        //.passwordEncoder(new MyPasswordEncoder);
        //这样，页面以明文的方式提交
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
                .withUser("zhangsan").password("123").roles("VIP1","VIP2")
                .and().passwordEncoder(new MyPasswordEncoder())
                .withUser("lisi").password("123").roles("VIP2","VIP3")
                .and().passwordEncoder(new MyPasswordEncoder())
                .withUser("wangwu").password("123").roles("VIP1","VIP3");
    }
}
