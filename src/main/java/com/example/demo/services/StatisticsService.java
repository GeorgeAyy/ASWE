package com.example.demo.services;

import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.models.Order;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public double getSalesToday() {
        LocalDate today = LocalDate.now();
        List<Order> ordersToday = orderRepository.findByOrderDate(today.atStartOfDay(),
                today.plusDays(1).atStartOfDay());
        return ordersToday.stream().mapToDouble(Order::getTotal).sum();
    }

    public double getSalesThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atStartOfDay();
        List<Order> ordersThisMonth = orderRepository.findByOrderDate(startOfMonth, endOfMonth);
        return ordersThisMonth.stream().mapToDouble(Order::getTotal).sum();
    }

    public int getAllCustomersCount() {
        return (int) userRepository.count();
    }

    public int getOrdersPlacedToday() {
        LocalDate today = LocalDate.now();
        return orderRepository.countByOrderDate(today.atStartOfDay(), today.plusDays(1).atStartOfDay());
    }

    public int getOrdersPlacedThisMonth() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atStartOfDay();
        return orderRepository.countByOrderDate(startOfMonth, endOfMonth);
    }

    public double getTotalRevenue() {
        return orderRepository.findAll().stream().mapToDouble(Order::getTotal).sum();
    }
}
