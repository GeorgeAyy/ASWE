package com.example.demo.interceptor;



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
public class ItemInterceptor implements HandlerInterceptor {
     @Autowired
    private  ItemRepository ItemRepository;

    @Autowired
    private ItemImagesRepository imagesRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String itemIdStr = request.getParameter("id");
            System.out.println("item id : " + itemIdStr);
            if (itemIdStr != null && !itemIdStr.isEmpty()) {
                Long itemId = Long.parseLong(itemIdStr);
                Item item = this.ItemRepository.findByItemId(itemId);
                System.out.println("Items by id: " + item);
                modelAndView.addObject("item", item);
                 List<ItemImages> itemImages = this.imagesRepository.findByItemItemId(item.getItemId());
                System.out.println("images: " + itemImages);
                modelAndView.addObject("itemImages", itemImages);
            }
        }
    }
}
