package com.example.demo.models;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date", nullable = false)
    private Timestamp orderDate;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "total", nullable = false)
    private double total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;

    // Getters and setters

    public Order() {
    }

    public Order(Long orderId, User user, Timestamp orderDate, String orderStatus, String address, String phoneNumber,
            String paymentMethod, double total, List<OrderItems> orderItems) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.paymentMethod = paymentMethod;
        this.total = total;
        this.orderItems = orderItems;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItems> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(user, order.user) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(address, order.address) &&
                Objects.equals(phoneNumber, order.phoneNumber) &&
                Objects.equals(paymentMethod, order.paymentMethod) &&
                total == order.total &&
                Objects.equals(orderItems, order.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, user, orderDate, orderStatus, address, phoneNumber, paymentMethod, total,
                orderItems);
    }

    @Override
    public String toString() {
        return "{" +
                " orderId='" + getOrderId() + "'" +
                ", user='" + getUser() + "'" +
                ", orderDate='" + getOrderDate() + "'" +
                ", orderStatus='" + getOrderStatus() + "'" +
                ", address='" + getAddress() + "'" +
                ", phoneNumber='" + getPhoneNumber() + "'" +
                ", paymentMethod='" + getPaymentMethod() + "'" +
                ", total='" + getTotal() + "'" +
                ", orderItems='" + getOrderItems() + "'" +
                "}";
    }
}
