package com.example.demo.dto;

import java.util.List;
import java.util.Objects;

public class WishlistDTO {
    private Long wishlistId;
    private String itemTitle;
    private String itemCategory;
    private String itemBrand;
    private double itemPrice;
    private int itemQuantity;
    private double itemOffers;
    private String itemDetails;
    private List<String> itemImages;

    // Constructors
    public WishlistDTO() {
    }

    public WishlistDTO(Long wishlistId, String itemTitle, String itemCategory, String itemBrand, double itemPrice,
            int itemQuantity, double itemOffers, String itemDetails, List<String> itemImages) {
        this.wishlistId = wishlistId;
        this.itemTitle = itemTitle;
        this.itemCategory = itemCategory;
        this.itemBrand = itemBrand;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemOffers = itemOffers;
        this.itemDetails = itemDetails;
        this.itemImages = itemImages;
    }

    // Getters and Setters

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemOffers() {
        return itemOffers;
    }

    public void setItemOffers(double itemOffers) {
        this.itemOffers = itemOffers;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public List<String> getItemImages() {
        return itemImages;
    }

    public void setItemImages(List<String> itemImages) {
        this.itemImages = itemImages;
    }

    // equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof WishlistDTO))
            return false;
        WishlistDTO that = (WishlistDTO) o;
        return Double.compare(that.itemPrice, itemPrice) == 0 &&
                itemQuantity == that.itemQuantity &&
                Double.compare(that.itemOffers, itemOffers) == 0 &&
                Objects.equals(wishlistId, that.wishlistId) &&
                Objects.equals(itemTitle, that.itemTitle) &&
                Objects.equals(itemCategory, that.itemCategory) &&
                Objects.equals(itemBrand, that.itemBrand) &&
                Objects.equals(itemDetails, that.itemDetails) &&
                Objects.equals(itemImages, that.itemImages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishlistId, itemTitle, itemCategory, itemBrand, itemPrice, itemQuantity, itemOffers,
                itemDetails, itemImages);
    }

    @Override
    public String toString() {
        return "WishlistDTO{" +
                "wishlistId=" + wishlistId +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", itemBrand='" + itemBrand + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemQuantity=" + itemQuantity +
                ", itemOffers=" + itemOffers +
                ", itemDetails='" + itemDetails + '\'' +
                ", itemImages=" + itemImages +
                '}';
    }
}
