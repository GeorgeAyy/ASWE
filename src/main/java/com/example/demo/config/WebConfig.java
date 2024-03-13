package com.example.demo.config;

import com.example.demo.interceptor.CategoryLocationInterceptor;
import com.example.demo.interceptor.ItemInterceptor;
import com.example.demo.interceptor.ProductListInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CategoryLocationInterceptor categoryLocationInterceptor;
    private final ProductListInterceptor productListInterceptor;
    private final ItemInterceptor itemInterceptor;

    public WebConfig(CategoryLocationInterceptor categoryLocationInterceptor,ProductListInterceptor productListInterceptor,
    ItemInterceptor itemInterceptor) {
        this.categoryLocationInterceptor = categoryLocationInterceptor;
        this.productListInterceptor=productListInterceptor;
        this.itemInterceptor= itemInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(categoryLocationInterceptor);
        registry.addInterceptor(productListInterceptor);
        registry.addInterceptor(itemInterceptor);

    }
}