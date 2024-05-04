package com.example.demo.controllers;
import com.example.demo.models.Item;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.services.CartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/cart")
public class CartController {
   @Autowired
   private ItemRepository itemRepository;

   @Autowired
   private CartRepository cartRepository;

   @Autowired
    private CartService cartService;

     @GetMapping("")
    public ModelAndView getproductcart(HttpSession session) {
        ModelAndView mav = new ModelAndView("cart.html");
        Long userId = (Long) session.getAttribute("username");
        mav.addObject("items", cartService.getItemsInCart(userId));
        mav.addObject("username", (long) session.getAttribute("username"));
        return mav;
    }
    @PostMapping("/add")
    public void addtocart(@RequestParam("itemId") Long itemId,HttpSession session) {
        Long username = (Long) session.getAttribute("username");
        cartService.addToCart(itemId,username);
     
    }

    @PostMapping("/updateQuantity")
    public void updateCart(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int quantity, HttpSession session) {
        
        Long userId = (Long) session.getAttribute("username");
        cartService.updateCartItemQuantity(itemId, userId, quantity);

    }

    @PostMapping("/remove")
     public @ResponseBody String removeItemFromCart(@RequestParam("itemId") Long itemId, HttpSession session) {
        Long userId = (Long) session.getAttribute("username");
        try {
            cartService.removeItemFromCart(itemId, userId);
            return "Item removed successfully";
        } catch (Exception e) {
            return "Error removing item: " + e.getMessage();
        }
    }
}
    
