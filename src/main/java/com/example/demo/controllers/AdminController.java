package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.UserRepository;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("admin_templates/dashboard.html");
        mav.addObject("title", "Dashboard");
        return mav;
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashboard() {
        ModelAndView mav = new ModelAndView("admin_templates/dashboard.html");
        mav.addObject("title", "Dashboard");
        return mav;
    }
    
    @GetMapping("/orders")
    public ModelAndView getOrders() {
        ModelAndView mav = new ModelAndView("admin_templates/orders.html");
        mav.addObject("title", "Orders");
        return mav;
    }
    
    @GetMapping("/products")
    public ModelAndView getProducts() {
        ModelAndView mav = new ModelAndView("admin_templates/products.html");
        mav.addObject("title", "Products");
        return mav;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {

        ModelAndView mav = new ModelAndView("admin_templates/users.html");
        mav.addObject("title", "Users");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }
}
