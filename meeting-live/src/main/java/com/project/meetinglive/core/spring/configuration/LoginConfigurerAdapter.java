package com.project.meetinglive.core.spring.configuration;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.meetinglive.core.spring.interceptor.impl.HandlerAuthLoginContextInterceptor;

/**
 * 拦截器
 * @author hejinguo
 * @version $Id: LoginConfigurerAdapter.java, v 0.1 2019年11月17日 下午5:36:02
 */
@Configuration
public class LoginConfigurerAdapter implements WebMvcConfigurer {
    @Resource
    private HandlerAuthLoginContextInterceptor handlerAuthLoginContextInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        InterceptorRegistration registration = registry
            .addInterceptor(handlerAuthLoginContextInterceptor);
        registration.addPathPatterns("/api/**", "/pc/**");
    }
}
