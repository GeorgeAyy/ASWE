package com.example.demo.models;

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
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "item_quantity", nullable = false)
    private int itemQuantity;

    // Add getters and setters

    public OrderItems() {
    }

    public OrderItems(Long orderItemId, Orders order, Item item, int itemQuantity) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    public Long getOrderItemId() {
        return this.orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Orders getOrder() {
        return this.order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public OrderItems orderItemId(Long orderItemId) {
        setOrderItemId(orderItemId);
        return this;
    }

    public OrderItems order(Orders order) {
        setOrder(order);
        return this;
    }

    public OrderItems item(Item item) {
        setItem(item);
        return this;
    }

    public OrderItems itemQuantity(int itemQuantity) {
        setItemQuantity(itemQuantity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderItems)) {
            return false;
        }
        OrderItems orderItems = (OrderItems) o;
        return Objects.equals(orderItemId, orderItems.orderItemId) && Objects.equals(order, orderItems.order) && Objects.equals(item, orderItems.item) && itemQuantity == orderItems.itemQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, order, item, itemQuantity);
    }

    @Override
    public String toString() {
        return "{" +
            " orderItemId='" + getOrderItemId() + "'" +
            ", order='" + getOrder() + "'" +
            ", item='" + getItem() + "'" +
            ", itemQuantity='" + getItemQuantity() + "'" +
            "}";
    }

}
