package com.ly.springboot.config;

import com.ly.springboot.component.LoginHandlerInterceptor;
import com.ly.springboot.component.MyLocaleResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author
 *
 * 实现WebMvcConfigurer来扩展SpringMVC的功能
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //Ctrl+o 快捷键打开可被重写的方法
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器访问 /aaa 也显示success页面
        registry.addViewController("/aaa").setViewName("success");
    }

    // 所有的WebMvcConfigurer组件会一起起作用
    @Bean // 将组件注册到容器
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            /*@Override
            public void addInterceptors(InterceptorRegistry registry) {
                String[] paths = {"/","/index.html","/tologin"};
                String[] resources = {"/js/**","/css/**","/scss/**","/images/**","/assets/**"};
                //静态资源：  *.css,*.js
                //springboot已经做好静态资源的映射
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .excludePathPatterns(paths)
                        .excludePathPatterns(resources);
            }*/

            /**
             * 解决跨域问题
             * */
            /*@Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")//设置允许跨域的路径
                        .allowedOrigins("*")//设置允许跨域请求的域名
                        .all
                        owCredentials(true)//是否允许证书 不再默认开启
                        .allowedMethods("GET","POST","DELETE","PUT")
                        .maxAge(3600);
            }*/

        };
        return configurer;
    }
    /**
     * 使用自己编写的区域信息实现国际化
     * */
    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }
}
