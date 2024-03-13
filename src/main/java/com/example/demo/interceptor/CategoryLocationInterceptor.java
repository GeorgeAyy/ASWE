package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Category;
import com.example.demo.models.Item;
import com.example.demo.models.Location;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.LocationRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@Component
public class CategoryLocationInterceptor implements HandlerInterceptor {
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final LocationRepository locationRepository;
    @Autowired
    private final ItemRepository ItemRepository;

    
    public CategoryLocationInterceptor(CategoryRepository categoryRepository, LocationRepository locationRepository, ItemRepository ItemRepository) {
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.ItemRepository = ItemRepository;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            List<Category> categories = categoryRepository.findAll();
            List<Location> locations = locationRepository.findAll();

            // Add categories and locations to the model
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("locations", locations);

            System.out.println("Interceptor: Added categories and locations to the model");
            System.out.println("Categories: " + categories);
            System.out.println("Locations: " + locations);

            String categoryName = request.getParameter("category");
            System.out.println("Category: " + categoryName);
            if (categoryName != null && !categoryName.isEmpty()) {
                List<Item> itemsByCategory = ItemRepository.findByItemCategory(categoryName);
                System.out.println("Items by category: " + itemsByCategory);
                modelAndView.addObject("itemsByCategory", itemsByCategory);
            }    
        }
    }
}
