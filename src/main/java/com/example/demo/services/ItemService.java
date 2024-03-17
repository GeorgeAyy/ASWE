package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ItemDTO;
import com.example.demo.models.Item;
import com.example.demo.models.ItemImages;
import com.example.demo.repositories.ItemImagesRepository;
import com.example.demo.repositories.ItemRepository;
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemImagesRepository itemImagesRepository;
    public void createItem(ItemDTO itemDTO, List<String> uploadedImagePaths) {
        try {
            // Perform validation if needed
            System.out.println("Creating item: " + itemDTO);
            // Save item to database
            Item item = new Item(
                itemDTO.getProductName(),
                itemDTO.getCategory(),
                itemDTO.getBrand(),
                itemDTO.getPrice(),
                itemDTO.getDescription(),
                itemDTO.getQuantity(),
                itemDTO.getOffers()
            );
            itemRepository.save(item);
    
            // Save image paths to database or handle image storage
            for (String imagePath : uploadedImagePaths) {
                System.out.println("Saving image path: " + imagePath);
                ItemImages itemImage = new ItemImages();
                itemImage.setItem(item);
                itemImage.setImagePath(imagePath);
                itemImagesRepository.save(itemImage);
            }
        } catch (Exception e) {
            // Handle error
            System.err.println("Error creating item: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
