package com.example.demo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.CartDTO;
import com.example.demo.models.Order;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CartService;
import com.example.demo.services.OrderService;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/placeOrder")
    public ModelAndView placeOrder(
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("payment") String paymentMethod,
            HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.warn("User not logged in. Redirecting to login page.");
            return new ModelAndView("redirect:/auth/login");
        }

        // Update user's address if not already set
        if (user.getUser_address() == null || user.getUser_address().isEmpty()) {
            user.setUser_address(address);
            userRepository.save(user);
            logger.info("Updated user address for user ID: {}", user.getUser_id());
        }

        // Get cart items
        List<CartDTO> items = cartService.getItemsInCart(user);
        logger.info("Fetched cart items for user ID: {}", user.getUser_id());

        // Create and save the order
        orderService.createOrder(user, items, address, phoneNumber, paymentMethod);
        logger.info("Order created for user ID: {}", user.getUser_id());

        // Clear the cart after placing the order
        cartService.clearCart(user);
        logger.info("Cleared cart for user ID: {}", user.getUser_id());

        // Redirect to the order confirmation page
        return new ModelAndView("redirect:/");
    }
}
