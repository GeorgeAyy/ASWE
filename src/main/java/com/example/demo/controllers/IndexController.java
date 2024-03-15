package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }
    @GetMapping("productlist")
    public ModelAndView getproductlist() {
        ModelAndView mav = new ModelAndView("productList.html");

        return mav;
    }
    @GetMapping("itempage")
    public ModelAndView getitempage() {
        ModelAndView mav = new ModelAndView("itemPage.html");
        return mav;
    }
    
    
}
