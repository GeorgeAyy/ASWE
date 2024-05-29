package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.dto.CartDTO;
import com.example.demo.models.Item;
import com.example.demo.models.Order;
import com.example.demo.models.OrderItems;
import com.example.demo.models.User;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private NotificationService notificationService;

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

    @Transactional(readOnly = true)
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderDTO> getOrdersByUser(User user) {
        return orderRepository.findByUser(user).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setOrderItems(order.getOrderItems().stream().map(this::convertToDTO).collect(Collectors.toList()));

        double orderSum = order.getOrderItems().stream()
                .mapToDouble(item -> item.getItemQuantity() * item.getItem().getItemPrice())
                .sum();
        orderDTO.setOrderSum(orderSum);

        return orderDTO;
    }

    private OrderItemDTO convertToDTO(OrderItems orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setItemQuantity(orderItem.getItemQuantity());
        orderItemDTO.setItemTitle(orderItem.getItem().getItemTitle());
        orderItemDTO.setItemPrice(orderItem.getItem().getItemPrice());
        return orderItemDTO;
    }

    public boolean changeOrderStatus(Long orderId, String orderStatus) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                order.setOrderStatus(orderStatus);
                orderRepository.save(order);

                User user = order.getUser();
                String notificationTitle = "Order #" + orderId;
                String notificationText = "Your order status has been updated to " + orderStatus;
                notificationService.createNotification(user, notificationTitle, notificationText);

                return true;
            }
        } catch (Exception e) {
            // Handle exception, e.g., log it
        }
        return false;
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
