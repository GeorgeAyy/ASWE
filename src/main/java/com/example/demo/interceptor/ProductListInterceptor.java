package com.example.demo.interceptor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Item;
import com.example.demo.repositories.ItemRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class ProductListInterceptor implements HandlerInterceptor {
    @Autowired
    private  ItemRepository ItemRepository;

  

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String categoryName = request.getParameter("category");
            System.out.println("Category: " + categoryName);
            if (categoryName != null && !categoryName.isEmpty()) {
                List<Item> itemsByCategory = this.ItemRepository.findByItemCategory(categoryName);
                System.out.println("Items by category: " + itemsByCategory);
                modelAndView.addObject("itemsByCategory", itemsByCategory);
            }
        }
    }
}
