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

import com.example.demo.repositories.UserRepository;


import com.example.demo.models.User;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public ModelAndView index(HttpSession session) {
        ModelAndView mav = new ModelAndView("index.html");
        mav.addObject("username", (String) session.getAttribute("username"));
        return mav;
    }

    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView("signup.html");
        User user = new User();
        mav.addObject("signupRequest", user);
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }
    
    @PostMapping("/login")
    public RedirectView getuser(@RequestParam("email") String email,
    @RequestParam("userPassword") String userPassword) {
        
        User dbUser= this.UserRepository.findByEmail(email);
        Boolean isPassword = BCrypt.checkpw(userPassword, dbUser.getUserPassword());
        if (isPassword) {
       
        return new RedirectView("/");}
        else{
            return new RedirectView("/auth/login");
        }
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
    
    @GetMapping("cart")
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
    @GetMapping("wishlist")
    public ModelAndView getwishlist() {
        ModelAndView mav = new ModelAndView("wishlist.html");
        return mav;
    }
}
