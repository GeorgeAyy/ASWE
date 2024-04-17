package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;






@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public ModelAndView index(HttpSession session) {
        ModelAndView mav = new ModelAndView("index.html");
        mav.addObject("username", (String) session.getAttribute("username"));
        return mav;
    }
    @GetMapping("productlist")
    public ModelAndView getproductlist(HttpSession session) {
        ModelAndView mav = new ModelAndView("productList.html");
        mav.addObject("username", (String) session.getAttribute("username"));
        return mav;
    }
    @GetMapping("itempage")
    public ModelAndView getitempage() {
        ModelAndView mav = new ModelAndView("itemPage.html");
        return mav;
    }
    
    @GetMapping("checkout")
    public ModelAndView getcart(HttpSession session) {
        ModelAndView mav = new ModelAndView("cart.html");
        mav.addObject("username", (String) session.getAttribute("username"));
        return mav;
    }
    
    @GetMapping("product/cart")
    public ModelAndView getproductcart() {
        ModelAndView mav = new ModelAndView("cart.html");
        return mav;
    }
    @GetMapping("product/wishlist")
    public ModelAndView getproductwishlist() {
        ModelAndView mav = new ModelAndView("wishlist.html");
        return mav;
    }
}
