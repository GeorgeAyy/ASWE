package com.example.demo.dto;

import java.util.Objects;

public class CartRequestDTO {
    private Long itemId;
    private Long userId;
    private int quantity;

    public CartRequestDTO() {
    }

    public CartRequestDTO(Long itemId, Long userId, int quantity) {
        this.itemId = itemId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartRequestDTO itemId(Long itemId) {
        setItemId(itemId);
        return this;
    }

    public CartRequestDTO userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public CartRequestDTO quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CartRequestDTO)) {
            return false;
        }
        CartRequestDTO cartRequestDTO = (CartRequestDTO) o;
        return Objects.equals(itemId, cartRequestDTO.itemId) && Objects.equals(userId, cartRequestDTO.userId)
                && quantity == cartRequestDTO.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, userId, quantity);
    }

    @Override
    public String toString() {
        return "{" +
                " itemId='" + getItemId() + "'" +
                ", userId='" + getUserId() + "'" +
                ", quantity='" + getQuantity() + "'" +
                "}";
    }
}
