package com.example.demo.config;

import com.example.demo.interceptor.CategoryLocationInterceptor;
import com.example.demo.interceptor.ItemInterceptor;
import com.example.demo.interceptor.NotificationInterceptor;
import com.example.demo.interceptor.ProductListInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private NotificationInterceptor notificationInterceptor;

    @Autowired
    private CategoryLocationInterceptor categoryLocationInterceptor;

    public WebConfig(NotificationInterceptor notificationIntercepto,
            CategoryLocationInterceptor categoryLocationInterceptor, ItemInterceptor itemInterceptor,
            ProductListInterceptor productListInterceptor) {
        this.notificationInterceptor = notificationInterceptor;
        this.categoryLocationInterceptor = categoryLocationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(notificationInterceptor).addPathPatterns("/**");
        registry.addInterceptor(categoryLocationInterceptor).addPathPatterns("/**");
    }
}