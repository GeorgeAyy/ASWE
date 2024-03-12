package com.example.demo.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {

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
    private boolean orderStatus;


    public Orders() {
    }

    public Orders(Long orderId, User user, Timestamp orderDate, boolean orderStatus) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
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

    public boolean isOrderStatus() {
        return this.orderStatus;
    }

    public boolean getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Orders orderId(Long orderId) {
        setOrderId(orderId);
        return this;
    }

    public Orders user(User user) {
        setUser(user);
        return this;
    }

    public Orders orderDate(Timestamp orderDate) {
        setOrderDate(orderDate);
        return this;
    }

    public Orders orderStatus(boolean orderStatus) {
        setOrderStatus(orderStatus);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Orders)) {
            return false;
        }
        Orders orders = (Orders) o;
        return Objects.equals(orderId, orders.orderId) && Objects.equals(user, orders.user) && Objects.equals(orderDate, orders.orderDate) && orderStatus == orders.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, user, orderDate, orderStatus);
    }

    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            ", user='" + getUser() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", orderStatus='" + isOrderStatus() + "'" +
            "}";
    }
   

}