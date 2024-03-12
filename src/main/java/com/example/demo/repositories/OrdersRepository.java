package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{
    
}
