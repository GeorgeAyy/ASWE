package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.CartService;
import com.example.demo.services.ItemService;
import com.example.demo.services.UserService;
import com.example.demo.services.WishlistService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Item;
import com.example.demo.models.User;
import com.example.demo.models.Wishlist;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private static final Logger logger = LoggerFactory.getLogger(WishlistController.class);

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @GetMapping("")
    public ModelAndView showWishlist(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            ModelAndView mav = new ModelAndView("wishlist");
            mav.addObject("wishlistItems", wishlistService.getWishlistItems(user));
            mav.addObject("user", user);
            return mav;
        }
        return new ModelAndView("redirect:/auth/login");
    }

    @PostMapping("/add")
    public String addToWishlist(@RequestParam Long itemId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            logger.info("User ID: {} is trying to add item ID: {} to wishlist", user.getUser_id(), itemId);
            Item item = itemService.getItemById(itemId);
            if (item != null) {
                Wishlist wishlist = new Wishlist();
                wishlist.setUser(user);
                wishlist.setItem(item);
                wishlistService.addWishlistItem(wishlist);
                logger.info("Item ID: {} added to wishlist for user ID: {}", itemId, user.getUser_id());
            } else {
                logger.warn("Item ID: {} not found", itemId);
            }
        } else {
            logger.warn("No user found in session");
        }
        return "redirect:/wishlist"; // Adjust the redirect URL as needed
    }

    @DeleteMapping("/remove")
    @ResponseBody
    public ModelAndView removeFromWishlist(@RequestParam Long wishlistId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            logger.warn("User not logged in. Cannot remove item from wishlist.");
            return new ModelAndView("redirect:/auth/login");
        }
        wishlistService.removeWishlistItem(wishlistId);
        logger.info("Item removed from wishlist for user ID: {}", user.getUser_id());
        return new ModelAndView("redirect:/wishlist"); // Adjust the redirect URL as needed

    }
}
