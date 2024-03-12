package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{
    
}
