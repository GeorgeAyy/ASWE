package com.example.demo.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Item;
import com.example.demo.models.ItemImages;
import com.example.demo.repositories.ItemImagesRepository;
import com.example.demo.repositories.ItemRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class ProductListInterceptor implements HandlerInterceptor {
    @Autowired
    private  ItemRepository ItemRepository;

    @Autowired
    private ItemImagesRepository imagesRepository;
  

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String categoryName = request.getParameter("category");
            System.out.println("Category: " + categoryName);
            if (categoryName != null && !categoryName.isEmpty()) {
                List<Item> itemsByCategory = this.ItemRepository.findByItemCategory(categoryName);
                System.out.println("Items by category: " + itemsByCategory);
                List<ItemImages> allItemImages = new ArrayList<>();
                for(Item item:itemsByCategory){
                    List<ItemImages> itemImages = this.imagesRepository.findByItemItemId(item.getItemId());
                    System.out.println("images: " + itemImages);
                    allItemImages.addAll(itemImages);
                }
               
                modelAndView.addObject("itemImages", allItemImages);
                System.out.println("allllimages: " + allItemImages);


            }
        }
    }
}
