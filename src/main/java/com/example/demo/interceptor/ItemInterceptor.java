package com.example.demo.interceptor;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Item;
import com.example.demo.repositories.ItemRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class ItemInterceptor implements HandlerInterceptor {
     @Autowired
    private  ItemRepository ItemRepository;

  

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String itemIdStr = request.getParameter("id");
            System.out.println("item id yarb: " + itemIdStr);
            if (itemIdStr != null && !itemIdStr.isEmpty()) {
                Long itemId = Long.parseLong(itemIdStr);
                Item item = this.ItemRepository.findByItemId(itemId);
                System.out.println("Items by id: " + item);
                modelAndView.addObject("item", item);
            }
        }
    }
}
