package com.example.demo.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OrderDTO {
    private Long orderId;
    private Date orderDate;
    private List<OrderItemDTO> orderItems;
    private String orderStatus;
    private double orderSum;

    public OrderDTO(Long orderId, Date orderDate, List<OrderItemDTO> orderItems, String orderStatus, double orderSum) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.orderSum = orderSum;
    }

    public double getOrderSum() {
        return this.orderSum;
    }

    public void setOrderSum(double orderSum) {
        this.orderSum = orderSum;
    }

    public OrderDTO orderSum(double orderSum) {
        setOrderSum(orderSum);
        return this;
    }

    public OrderDTO() {
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItemDTO> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderDTO orderId(Long orderId) {
        setOrderId(orderId);
        return this;
    }

    public OrderDTO orderDate(Date orderDate) {
        setOrderDate(orderDate);
        return this;
    }

    public OrderDTO orderItems(List<OrderItemDTO> orderItems) {
        setOrderItems(orderItems);
        return this;
    }

    public OrderDTO orderStatus(String orderStatus) {
        setOrderStatus(orderStatus);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderDTO)) {
            return false;
        }
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(orderId, orderDTO.orderId) && Objects.equals(orderDate, orderDTO.orderDate)
                && Objects.equals(orderItems, orderDTO.orderItems) && orderStatus == orderDTO.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, orderItems, orderStatus);
    }

    @Override
    public String toString() {
        return "{" +
                " orderId='" + getOrderId() + "'" +
                ", orderDate='" + getOrderDate() + "'" +
                ", orderItems='" + getOrderItems() + "'" +
                ", orderStatus='" + getOrderStatus() + "'" +
                "}";
    }
}