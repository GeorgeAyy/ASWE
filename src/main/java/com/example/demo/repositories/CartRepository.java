package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Cart;
import com.example.demo.models.Item;
import com.example.demo.models.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserAndItem(User user, Item item);

    List<Cart> findByUser(User user);

    void deleteByUser(User user);

}