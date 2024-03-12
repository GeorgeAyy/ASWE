package com.example.demo.config;

import com.example.demo.interceptor.CategoryLocationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CategoryLocationInterceptor categoryLocationInterceptor;

    public WebConfig(CategoryLocationInterceptor categoryLocationInterceptor) {
        this.categoryLocationInterceptor = categoryLocationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(categoryLocationInterceptor);
    }
}