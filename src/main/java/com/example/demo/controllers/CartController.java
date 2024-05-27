package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.CartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.models.User;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public ModelAndView getproductcart(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.warn("User not logged in. Redirecting to login page.");
            return new ModelAndView("redirect:/login");
        }

        logger.info("Fetching cart items for user with ID: {}", user.getUser_id());
        ModelAndView mav = new ModelAndView("cart.html");
        mav.addObject("items", cartService.getItemsInCart(user));
        mav.addObject("user", user);
        logger.info("Fetched cart items for user with ID: {}", user.getUser_id());
        return mav;
    }

    @PostMapping("/add")
    public void addToCart(@RequestParam("itemId") Long itemId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.warn("User not logged in. Cannot add item to cart.");
            return;
        }

        logger.info("Adding item with ID: {} to cart for user with ID: {} with quantity: {}", itemId, user.getUser_id(),
                quantity);
        cartService.addToCart(itemId, user, quantity);
        logger.info("Item with ID: {} added to cart for user with ID: {}", itemId, user.getUser_id());
    }

    @PostMapping("/updateQuantity")
    public void updateCart(@RequestParam("itemId") Long itemId,
            @RequestParam("quantity") int quantity,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.warn("User not logged in. Cannot update cart item quantity.");
            return;
        }

        logger.info("Updating quantity of item with ID: {} in cart for user with ID: {} to {}", itemId,
                user.getUser_id(), quantity);
        cartService.updateCartItemQuantity(itemId, user, quantity);
        logger.info("Updated quantity of item with ID: {} in cart for user with ID: {}", itemId, user.getUser_id());
    }

    @PostMapping("/remove")
    public @ResponseBody String removeItemFromCart(@RequestParam("itemId") Long itemId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.warn("User not logged in. Cannot remove item from cart.");
            return "User not logged in.";
        }

        logger.info("Removing item with ID: {} from cart for user with ID: {}", itemId, user.getUser_id());
        try {
            cartService.removeItemFromCart(itemId, user);
            logger.info("Removed item with ID: {} from cart for user with ID: {}", itemId, user.getUser_id());
            return "Item removed successfully";
        } catch (Exception e) {
            logger.error("Error removing item with ID: {} from cart for user with ID: {}: {}", itemId,
                    user.getUser_id(), e.getMessage());
            return "Error removing item: " + e.getMessage();
        }
    }
}
