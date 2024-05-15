package com.example.demo.dto;

import java.util.List;
import java.util.Objects;

public class CartDTO {
    private Long itemId;
    private String itemName;
    private double itemPrice;
    private int quantity;
    private List<String> images;



    public CartDTO() {
    }

    public CartDTO(Long itemId, String itemName, double itemPrice, int quantity, List<String> images) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.images = images;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getImages() {
        return this.images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public CartDTO itemId(Long itemId) {
        setItemId(itemId);
        return this;
    }

    public CartDTO itemName(String itemName) {
        setItemName(itemName);
        return this;
    }

    public CartDTO itemPrice(double itemPrice) {
        setItemPrice(itemPrice);
        return this;
    }

    public CartDTO quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public CartDTO images(List<String> images) {
        setImages(images);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CartDTO)) {
            return false;
        }
        CartDTO cartDTO = (CartDTO) o;
        return Objects.equals(itemId, cartDTO.itemId) && Objects.equals(itemName, cartDTO.itemName) && itemPrice == cartDTO.itemPrice && quantity == cartDTO.quantity && Objects.equals(images, cartDTO.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, itemPrice, quantity, images);
    }

    @Override
    public String toString() {
        return "{" +
            " itemId='" + getItemId() + "'" +
            ", itemName='" + getItemName() + "'" +
            ", itemPrice='" + getItemPrice() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", images='" + getImages() + "'" +
            "}";
    }
    

    
}
