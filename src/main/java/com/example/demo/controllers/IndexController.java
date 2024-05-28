package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.OrderDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

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

    @GetMapping("/account")
    public ModelAndView accountPage(HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            ModelAndView mav = new ModelAndView("account.html");
            List<OrderDTO> orders = orderService.getOrdersByUser(user);

            // Add data to the model
            mav.addObject("user", user);
            mav.addObject("orders", orders);

            return mav;
        } else {
            // Redirect to login if user is not logged in
            return new ModelAndView("redirect:/auth/login");
        }

    }

    @GetMapping("logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        ModelAndView mav = new ModelAndView("logout.html");
        return mav;
    }

    @GetMapping("orderConfirmed")
    public ModelAndView getorderconfirmed(HttpSession session) {
        ModelAndView mav = new ModelAndView("orderConfirmed.html");
        mav.addObject("user", (User) session.getAttribute("user"));
        return mav;
    }
}
