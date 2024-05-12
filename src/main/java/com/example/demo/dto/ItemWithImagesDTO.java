package com.example.demo.dto;

import com.example.demo.models.Item;
import com.example.demo.models.ItemImages;

public class ItemWithImagesDTO {
    private Item item;
    private Iterable<ItemImages> images;

    public ItemWithImagesDTO(Item item, Iterable<ItemImages> images) {
        this.item = item;
        this.images = images;
    }

    public Item getItem() {
        return item;
    }

    public Iterable<ItemImages> getImages() {
        return images;
    }
}
