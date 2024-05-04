package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.models.Item;
import com.example.demo.models.User;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    public void addToCart(Long itemId, Long username) {
        User user = this.userRepository.findById(username).get();
        Item item = this.itemRepository.findByItemId(itemId);

        if (user != null && item != null) {
            // Check if item quantity is greater than 0
            if (item.getItemQuantity() > 0) {
                // Reduce item quantity by 1
                item.setItemQuantity(item.getItemQuantity() - 1);
                this.itemRepository.save(item);

                // Check if the item is already in the user's cart
                Cart existingCartItem = this.cartRepository.findByUserAndItem(user, item);
                System.out.println("existingCartItem" + existingCartItem);
                if (existingCartItem != null) {
                    // Increment quantity in the cart
                    existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
                    this.cartRepository.save(existingCartItem);
                } else {
                    // Add item to cart
                    Cart cart = new Cart();
                    cart.setItem(item);
                    cart.setUser(user);
                    cart.setQuantity(1);
                    this.cartRepository.save(cart);
                }
            } else {
                throw new IllegalArgumentException("Item is out of stock");
            }
        } else {
            throw new IllegalArgumentException("User or item not found");
        }
    }

    public List getItemsInCart(Long userId) {
        User user = this.userRepository.findById(userId).get();
        List<Cart> items = this.cartRepository.findByUser(user);
        System.out.println("items" + items);
        return items;
    }

    public void updateCartItemQuantity(Long itemId,Long userId,int quantity){
        

        User user = this.userRepository.findById(userId).get();
        Item item = this.itemRepository.findByItemId(itemId);

        Cart cartItem = cartRepository.findByUserAndItem(user, item);
        
        if (cartItem != null) {
            // Calculate the difference in quantity
            int quantityDifference = quantity - cartItem.getQuantity();
            
            // Update quantity in cart
            cartItem.setQuantity(quantity);
            cartRepository.save(cartItem);
            
            // Update quantity in item table
            item.setItemQuantity(item.getItemQuantity() - quantityDifference);

            itemRepository.save(item);
        }
    }
    public void removeItemFromCart(Long itemId, Long userId) {
        User user = this.userRepository.findById(userId).get();
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        Cart cartItem = cartRepository.findByUserAndItem(user, item);
        if (cartItem != null) {
            cartRepository.delete(cartItem);
            // Optionally adjust the item stock quantity if needed
            item.setItemQuantity(item.getItemQuantity() + cartItem.getQuantity());
            itemRepository.save(item);
        }
    }
}