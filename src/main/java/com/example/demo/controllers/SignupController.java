package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/signup")
public class SignupController {

    @GetMapping("/")
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView("signup.html");
        return mav;
    }   
}