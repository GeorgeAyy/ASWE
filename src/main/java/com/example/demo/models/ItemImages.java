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
@Table(name = "item_images")
public class ItemImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    // Add getters and setters

    public ItemImages() {
    }

    public ItemImages(Long imageId, Item item, String imagePath) {
        this.imageId = imageId;
        this.item = item;
        this.imagePath = imagePath;
    }

    public Long getImageId() {
        return this.imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ItemImages imageId(Long imageId) {
        setImageId(imageId);
        return this;
    }

    public ItemImages item(Item item) {
        setItem(item);
        return this;
    }

    public ItemImages imagePath(String imagePath) {
        setImagePath(imagePath);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ItemImages)) {
            return false;
        }
        ItemImages itemImages = (ItemImages) o;
        return Objects.equals(imageId, itemImages.imageId) && Objects.equals(item, itemImages.item) && Objects.equals(imagePath, itemImages.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, item, imagePath);
    }

    @Override
    public String toString() {
        return "{" +
            " imageId='" + getImageId() + "'" +
            ", item='" + getItem() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            "}";
    }

}