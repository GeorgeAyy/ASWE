package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CartRequestDTO;
import com.example.demo.models.Cart;
import com.example.demo.models.Item;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ItemImagesRepository;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    private RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8081"; // Base URL for the REST API

    public CartService() {
        this.restTemplate = new RestTemplate(); // Initialize a new RestTemplate instance
    }

    public void addToCart(Long itemId, User user, int quantity) {
        CartRequestDTO cartRequest = new CartRequestDTO();
        cartRequest.setItemId(itemId);
        cartRequest.setUserId(user.getUser_id());
        cartRequest.setQuantity(quantity); // Set the quantity from the parameter

        logger.info("Adding item with ID: {} to cart for user with ID: {} with quantity: {}", itemId, user.getUser_id(),
                quantity);
        String url = baseUrl + "/cart/add";
        this.restTemplate.postForObject(url, cartRequest, Void.class);
        logger.info("Item added to cart successfully");
    }

    public List<CartDTO> getItemsInCart(User user) {
        String url = baseUrl + "/cart/items/" + user.getUser_id();
        logger.info("Fetching items in cart for user with ID: {}", user.getUser_id());
        ResponseEntity<List<CartDTO>> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartDTO>>() {
                });
        List<CartDTO> cartItems = response.getBody();
        logger.info("Fetched {} items in cart for user with ID: {}", cartItems.size(), user.getUser_id());
        return cartItems;
    }

    public void updateCartItemQuantity(Long itemId, User user, int quantity) {
        CartRequestDTO cartRequest = new CartRequestDTO();
        cartRequest.setItemId(itemId);
        cartRequest.setUserId(user.getUser_id());
        cartRequest.setQuantity(quantity);

        logger.info("Updating cart item with ID: {} for user with ID: {} to quantity: {}", itemId, user.getUser_id(),
                quantity);
        String url = baseUrl + "/cart/update";
        this.restTemplate.put(url, cartRequest);
        logger.info("Cart item updated successfully");
    }

    public void removeItemFromCart(Long itemId, User user) {
        String url = baseUrl + "/cart/remove/" + user.getUser_id() + "/" + itemId;
        logger.info("Removing item with ID: {} from cart for user with ID: {}", itemId, user.getUser_id());
        this.restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        logger.info("Item removed from cart successfully");
    }

    // Method to clear the cart
    public void clearCart(User user) {
        cartRepository.deleteByUser(user);
    }

}