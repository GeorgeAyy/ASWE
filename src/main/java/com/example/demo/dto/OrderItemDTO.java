package com.example.demo.dto;

import java.util.Objects;

public class OrderItemDTO {
    private int itemQuantity;
    private String itemTitle;
    private double itemPrice;

    public OrderItemDTO() {
    }

    public OrderItemDTO(int itemQuantity, String itemTitle, double itemPrice) {
        this.itemQuantity = itemQuantity;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemTitle() {
        return this.itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public OrderItemDTO itemQuantity(int itemQuantity) {
        setItemQuantity(itemQuantity);
        return this;
    }

    public OrderItemDTO itemTitle(String itemTitle) {
        setItemTitle(itemTitle);
        return this;
    }

    public OrderItemDTO itemPrice(double itemPrice) {
        setItemPrice(itemPrice);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderItemDTO)) {
            return false;
        }
        OrderItemDTO orderItemDTO = (OrderItemDTO) o;
        return itemQuantity == orderItemDTO.itemQuantity && Objects.equals(itemTitle, orderItemDTO.itemTitle)
                && itemPrice == orderItemDTO.itemPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemQuantity, itemTitle, itemPrice);
    }

    @Override
    public String toString() {
        return "{" +
                " itemQuantity='" + getItemQuantity() + "'" +
                ", itemTitle='" + getItemTitle() + "'" +
                ", itemPrice='" + getItemPrice() + "'" +
                "}";
    }

}
