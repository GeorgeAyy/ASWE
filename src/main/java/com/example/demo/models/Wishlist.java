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
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    

    public Wishlist() {
    }

    public Wishlist(Long wishlistId, User user, Item item) {
        this.wishlistId = wishlistId;
        this.user = user;
        this.item = item;
    }

    public Long getWishlistId() {
        return this.wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
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

    public Wishlist wishlistId(Long wishlistId) {
        setWishlistId(wishlistId);
        return this;
    }

    public Wishlist user(User user) {
        setUser(user);
        return this;
    }

    public Wishlist item(Item item) {
        setItem(item);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Wishlist)) {
            return false;
        }
        Wishlist wishlist = (Wishlist) o;
        return Objects.equals(wishlistId, wishlist.wishlistId) && Objects.equals(user, wishlist.user) && Objects.equals(item, wishlist.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishlistId, user, item);
    }

    @Override
    public String toString() {
        return "{" +
            " wishlistId='" + getWishlistId() + "'" +
            ", user='" + getUser() + "'" +
            ", item='" + getItem() + "'" +
            "}";
    }
}