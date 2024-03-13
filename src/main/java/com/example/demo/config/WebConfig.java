package com.example.demo.config;

import com.example.demo.interceptor.CategoryLocationInterceptor;
import com.example.demo.interceptor.ProductListInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CategoryLocationInterceptor categoryLocationInterceptor;
    private final ProductListInterceptor productListInterceptor;

    public WebConfig(CategoryLocationInterceptor categoryLocationInterceptor,ProductListInterceptor productListInterceptor) {
        this.categoryLocationInterceptor = categoryLocationInterceptor;
        this.productListInterceptor=productListInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(categoryLocationInterceptor);
        registry.addInterceptor(productListInterceptor);
    }
}