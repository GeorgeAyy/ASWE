package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDTO;
import com.example.demo.models.Item;
import com.example.demo.models.Order;
import com.example.demo.models.OrderItems;
import com.example.demo.models.User;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.OrderRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    public Order createOrder(User user, List<CartDTO> items, String address, String phoneNumber, String paymentMethod) {
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setPaymentMethod(paymentMethod);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setOrderStatus("Processing"); // Set initial status to "Processing"
        order.setTotal(calculateTotal(items));
        logger.info("Creating order for user ID: {}", user.getUser_id());

        List<OrderItems> orderItemsList = items.stream()
                .map(cartItem -> {
                    OrderItems orderItem = new OrderItems();
                    orderItem.setOrder(order);

                    // Find the corresponding item entity
                    Item item = itemRepository.findById(cartItem.getItemId())
                            .orElseThrow(() -> {
                                logger.error("Item not found for item ID: {}", cartItem.getItemId());
                                return new RuntimeException("Item not found");
                            });

                    // Reduce the item quantity
                    item.setItemQuantity(item.getItemQuantity() - cartItem.getQuantity());
                    itemRepository.save(item);
                    logger.info("Reduced item quantity for item ID: {}", item.getItemId());

                    orderItem.setItem(item);
                    orderItem.setItemQuantity(cartItem.getQuantity());
                    return orderItem;
                }).collect(Collectors.toList());

        order.setOrderItems(orderItemsList);
        logger.info("Order items set for order ID: {}", order.getOrderId());

        // Save the order to the database
        Order savedOrder = orderRepository.save(order);
        logger.info("Order saved with order ID: {}", savedOrder.getOrderId());
        return savedOrder;
    }

    private double calculateTotal(List<CartDTO> items) {
        double total = items.stream().mapToDouble(item -> item.getItemPrice() * item.getQuantity()).sum();
        logger.info("Calculated total: {}", total);
        return total;
    }
}
