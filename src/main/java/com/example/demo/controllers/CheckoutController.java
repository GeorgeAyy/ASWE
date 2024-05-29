package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.CartDTO;
import com.example.demo.models.User;
import com.example.demo.services.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);

    @Autowired
    private CartService cartService;

    public CheckoutController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public ModelAndView getCheckoutPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.warn("User not logged in. Redirecting to login page.");
            return new ModelAndView("redirect:/auth/login");
        }

        logger.info("Fetching cart items for user with ID: {}", user.getUser_id());
        List<CartDTO> items = cartService.getItemsInCart(user);
        if (items == null) {
            logger.error("Failed to fetch cart items for user with ID: {}", user.getUser_id());
            items = new ArrayList<>(); // Ensure items is never null
        }

        double total = items.stream().mapToDouble(item -> item.getItemPrice() * item.getQuantity()).sum();

        ModelAndView mav = new ModelAndView("checkout");
        mav.addObject("items", items);
        mav.addObject("user", user);
        mav.addObject("size", items.size());
        mav.addObject("total", total);
        mav.addObject("address", user.getUser_address());
        logger.info("Fetched cart items for user with ID: {}", user.getUser_id());
        return mav;
    }
}
