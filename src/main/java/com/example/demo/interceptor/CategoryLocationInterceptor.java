package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Category;

import com.example.demo.models.Location;
import com.example.demo.repositories.CategoryRepository;

import com.example.demo.repositories.LocationRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@Component
public class CategoryLocationInterceptor implements HandlerInterceptor {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryLocationInterceptor(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            List<Category> categories = categoryRepository.findAll();

            // Add categories and locations to the model
            modelAndView.addObject("categories", categories);

            System.out.println("Interceptor: Added categories and locations to the model");
            System.out.println("Categories: " + categories);

        }
    }
}
