package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_title", nullable = false)
    private String itemTitle;

    @Column(name = "item_brand", nullable = false)
    private String itemBrand;

    @Column(name = "item_cat", nullable = false)
    private String itemCategory;

    @Column(name = "item_details", nullable = false, columnDefinition = "TEXT")
    private String itemDetails;

    @Column(name = "item_quantity", nullable = false)
    private int itemQuantity;

    @Column(name = "item_price", nullable = false)
    private double itemPrice;

    @Column(name = "item_offers", nullable = false)
    private double itemOffers;

    // Add getters and setters

    public Item() {
    }

    public Item(Long itemId, String itemTitle, String itemBrand, String itemCategory, String itemDetails, int itemQuantity, int itemPrice, int itemOffers) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemBrand = itemBrand;
        this.itemCategory = itemCategory;
        this.itemDetails = itemDetails;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.itemOffers = itemOffers;
    }
    
    public Item(String productName, String category, String brand, double price, String description, int quantity,
            double offers) {
        this.itemTitle = productName;
        this.itemCategory = category;
        this.itemBrand = brand;
        this.itemPrice = price;
        this.itemDetails = description;
        this.itemQuantity = quantity;
        this.itemOffers = offers;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return this.itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemBrand() {
        return this.itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemCategory() {
        return this.itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemDetails() {
        return this.itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemOffers() {
        return this.itemOffers;
    }

    public void setItemOffers(double itemOffers) {
        this.itemOffers = itemOffers;
    }

    public Item itemId(Long itemId) {
        setItemId(itemId);
        return this;
    }

    public Item itemTitle(String itemTitle) {
        setItemTitle(itemTitle);
        return this;
    }

    public Item itemBrand(String itemBrand) {
        setItemBrand(itemBrand);
        return this;
    }

    public Item itemCategory(String itemCategory) {
        setItemCategory(itemCategory);
        return this;
    }

    public Item itemDetails(String itemDetails) {
        setItemDetails(itemDetails);
        return this;
    }

    public Item itemQuantity(int itemQuantity) {
        setItemQuantity(itemQuantity);
        return this;
    }

    public Item itemPrice(int itemPrice) {
        setItemPrice(itemPrice);
        return this;
    }

    public Item itemOffers(int itemOffers) {
        setItemOffers(itemOffers);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) && Objects.equals(itemTitle, item.itemTitle) && Objects.equals(itemBrand, item.itemBrand) && Objects.equals(itemCategory, item.itemCategory) && Objects.equals(itemDetails, item.itemDetails) && itemQuantity == item.itemQuantity && itemPrice == item.itemPrice && itemOffers == item.itemOffers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemTitle, itemBrand, itemCategory, itemDetails, itemQuantity, itemPrice, itemOffers);
    }

    @Override
    public String toString() {
        return "{" +
            " itemId='" + getItemId() + "'" +
            ", itemTitle='" + getItemTitle() + "'" +
            ", itemBrand='" + getItemBrand() + "'" +
            ", itemCategory='" + getItemCategory() + "'" +
            ", itemDetails='" + getItemDetails() + "'" +
            ", itemQuantity='" + getItemQuantity() + "'" +
            ", itemPrice='" + getItemPrice() + "'" +
            ", itemOffers='" + getItemOffers() + "'" +
            "}";
    }

}