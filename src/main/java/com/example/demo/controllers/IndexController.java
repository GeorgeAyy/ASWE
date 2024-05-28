package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.User;
import com.example.demo.repositories.ItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public ModelAndView index(HttpSession session) {
        ModelAndView mav = new ModelAndView("index.html");
        mav.addObject("user", (User) session.getAttribute("user"));
        return mav;
    }

    @GetMapping("productlist")
    public ModelAndView getproductlist(HttpSession session) {
        ModelAndView mav = new ModelAndView("productList.html");
        mav.addObject("user", (User) session.getAttribute("user"));
        return mav;
    }

    @GetMapping("/item/{id}")
    public ModelAndView getitempage(HttpSession session, @PathVariable Long id) {
        ModelAndView mav = new ModelAndView("itemPage.html");
        mav.addObject("user", (User) session.getAttribute("user"));
        mav.addObject("itemId", id);
        return mav;
    }

    @GetMapping("product/wishlist")
    public ModelAndView getproductwishlist(HttpSession session) {
        ModelAndView mav = new ModelAndView("wishlist.html");
        mav.addObject("user", (User) session.getAttribute("user"));
        return mav;
    }

    @GetMapping("account")
    public ModelAndView getaccount(HttpSession session) {
        ModelAndView mav = new ModelAndView();

        // Check if there is a user in the session
        if (session != null && session.getAttribute("user") != null) {
            mav.setViewName("account.html");
            mav.addObject("user", (User) session.getAttribute("user"));
        } else {
            // User is not authenticated, render the registration/login prompt view
            mav.setViewName("redirect:/auth/login");
        }

        return mav;
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        ModelAndView mav = new ModelAndView("logout.html");
        return mav;
    }

    @GetMapping("wishlist")
    public ModelAndView getwishlist(HttpSession session) {
        ModelAndView mav = new ModelAndView("wishlist.html");
        mav.addObject("user", (User) session.getAttribute("user"));
        return mav;
    }
}
