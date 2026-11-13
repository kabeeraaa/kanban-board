package com.bej.kanbanService.config;

import com.bej.kanbanService.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    // comment
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterBean(@Value("${kanban.jwt.secret}") String signingKey) {
        FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JwtFilter(signingKey));
        filterRegistrationBean.addUrlPatterns("/api/v1/user/*");
        return filterRegistrationBean;
    }
}

