package com.example.demo.controllers;



import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.example.demo.services.CartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.models.User;


@RestController
@RequestMapping("/cart")
public class CartController {
   

   @Autowired
    private CartService cartService;

     @GetMapping("")
    public ModelAndView getproductcart(HttpSession session) {
        ModelAndView mav = new ModelAndView("cart.html");
        User user = (User) session.getAttribute("user");
        mav.addObject("items", cartService.getItemsInCart(user));
        mav.addObject("user", (User) session.getAttribute("user"));
        return mav;
    }
    @PostMapping("/add")
    public void addtocart(@RequestParam("itemId") Long itemId,HttpSession session) {
        User user = (User) session.getAttribute("user");
        cartService.addToCart(itemId,user);
     
    }

    @PostMapping("/updateQuantity")
    public void updateCart(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int quantity, HttpSession session) {
        
            User user = (User) session.getAttribute("user");
        cartService.updateCartItemQuantity(itemId, user, quantity);

    }

    @PostMapping("/remove")
     public @ResponseBody String removeItemFromCart(@RequestParam("itemId") Long itemId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            cartService.removeItemFromCart(itemId, user);
            return "Item removed successfully";
        } catch (Exception e) {
            return "Error removing item: " + e.getMessage();
        }
    }
}
    
