package com.example.demo.dto;
import java.util.List;
import java.util.Objects;

public class ItemDTO {
    private String productName;
    private String category;
    private String brand;
    private double price;
    private String description;
    private int quantity;
    private double offers;
    private List<String> uploadedImagePaths;


    public ItemDTO() {
    }

    public ItemDTO(String productName, String category, String brand, double price, String description, int quantity, double offers, List<String> uploadedImagePaths) {
        this.productName = productName;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.offers = offers;
        this.uploadedImagePaths = uploadedImagePaths;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOffers() {
        return this.offers;
    }

    public void setOffers(double offers) {
        this.offers = offers;
    }

    public List<String> getUploadedImagePaths() {
        return this.uploadedImagePaths;
    }

    public void setUploadedImagePaths(List<String> uploadedImagePaths) {
        this.uploadedImagePaths = uploadedImagePaths;
    }

    public ItemDTO productName(String productName) {
        setProductName(productName);
        return this;
    }

    public ItemDTO category(String category) {
        setCategory(category);
        return this;
    }

    public ItemDTO brand(String brand) {
        setBrand(brand);
        return this;
    }

    public ItemDTO price(double price) {
        setPrice(price);
        return this;
    }

    public ItemDTO description(String description) {
        setDescription(description);
        return this;
    }

    public ItemDTO quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public ItemDTO offers(double offers) {
        setOffers(offers);
        return this;
    }

    public ItemDTO uploadedImagePaths(List<String> uploadedImagePaths) {
        setUploadedImagePaths(uploadedImagePaths);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ItemDTO)) {
            return false;
        }
        ItemDTO itemDTO = (ItemDTO) o;
        return Objects.equals(productName, itemDTO.productName) && Objects.equals(category, itemDTO.category) && Objects.equals(brand, itemDTO.brand) && price == itemDTO.price && Objects.equals(description, itemDTO.description) && quantity == itemDTO.quantity && offers == itemDTO.offers && Objects.equals(uploadedImagePaths, itemDTO.uploadedImagePaths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, category, brand, price, description, quantity, offers, uploadedImagePaths);
    }

    @Override
    public String toString() {
        return "{" +
            " productName='" + getProductName() + "'" +
            ", category='" + getCategory() + "'" +
            ", brand='" + getBrand() + "'" +
            ", price='" + getPrice() + "'" +
            ", description='" + getDescription() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", offers='" + getOffers() + "'" +
            ", uploadedImagePaths='" + getUploadedImagePaths() + "'" +
            "}";
    }
    
    
}
