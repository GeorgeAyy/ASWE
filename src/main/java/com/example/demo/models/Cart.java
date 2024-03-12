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
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    // Add getters and setters

    public Cart() {
    }

    public Cart(Long cartId, User user, Item item, int quantity) {
        this.cartId = cartId;
        this.user = user;
        this.item = item;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return this.cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart cartId(Long cartId) {
        setCartId(cartId);
        return this;
    }

    public Cart user(User user) {
        setUser(user);
        return this;
    }

    public Cart item(Item item) {
        setItem(item);
        return this;
    }

    public Cart quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId) && Objects.equals(user, cart.user) && Objects.equals(item, cart.item) && quantity == cart.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, user, item, quantity);
    }

    @Override
    public String toString() {
        return "{" +
            " cartId='" + getCartId() + "'" +
            ", user='" + getUser() + "'" +
            ", item='" + getItem() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }

}