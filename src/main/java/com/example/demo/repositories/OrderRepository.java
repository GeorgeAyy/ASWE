package com.example.demo.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Order;
import com.example.demo.models.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByOrderDate(LocalDate orderDate);

    List<Order> findByOrderDateAfter(LocalDate date);

    @Query("SELECT SUM(o.total) FROM Order o WHERE o.orderDate >= :start AND o.orderDate < :end")
    List<Double> findMonthlySalesData(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT o FROM Order o WHERE o.orderDate >= :start AND o.orderDate < :end")
    List<Order> findByOrderDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderDate >= :start AND o.orderDate < :end")
    int countByOrderDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    List<Order> findAll();
}
