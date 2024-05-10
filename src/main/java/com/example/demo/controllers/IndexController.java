package com.example.demo.controllers;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.repositories.UserRepository;

import com.example.demo.repositories.UserRepository;



@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public ModelAndView index(HttpSession session) {
        ModelAndView mav = new ModelAndView("index.html");
        mav.addObject("username", (Long) session.getAttribute("username"));
        return mav;
    }
    @GetMapping("productlist")
    public ModelAndView getproductlist(HttpSession session) {
        ModelAndView mav = new ModelAndView("productList.html");
        mav.addObject("username", (Long) session.getAttribute("username"));
        return mav;
    }
    @GetMapping("itempage")
    public ModelAndView getitempage(HttpSession session) {
        ModelAndView mav = new ModelAndView("itemPage.html");
        mav.addObject("username", (Long) session.getAttribute("username"));
        return mav;
    }
    
   
    @GetMapping("product/wishlist")
    public ModelAndView getproductwishlist(HttpSession session) {
        ModelAndView mav = new ModelAndView("wishlist.html");
        mav.addObject("username", (Long) session.getAttribute("username"));
        return mav;
    }

    @GetMapping("account")
    public ModelAndView getaccount(HttpSession session) {
        ModelAndView mav = new ModelAndView("account.html");
        mav.addObject("username", (Long) session.getAttribute("username"));
        return mav;
    }

    @GetMapping("logout")
    public ModelAndView logout (HttpSession session) {
        session.invalidate(); 
        ModelAndView mav = new ModelAndView("logout.html");
        return mav;
    }
    
    
    @GetMapping("wishlist")
    public ModelAndView getwishlist() {
        ModelAndView mav = new ModelAndView("wishlist.html");
        return mav;
    }
}
