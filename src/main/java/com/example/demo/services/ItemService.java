package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
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
                    itemDTO.getOffers());
            itemRepository.save(item);

            // Save image paths to database or handle image storage
            for (String imagePath : uploadedImagePaths) {
                // Clean the image path to remove square brackets
                String cleanedImagePath = imagePath.replaceAll("\\[|\\]", "");
                System.out.println("Saving image path: " + cleanedImagePath);

                ItemImages itemImage = new ItemImages();
                itemImage.setItem(item);
                itemImage.setImagePath(cleanedImagePath);
                itemImagesRepository.save(itemImage);
            }

        } catch (Exception e) {
            // Handle error
            System.err.println("Error creating item: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void editItem(Long productId, ItemDTO itemDTO, List<String> uploadedImagePaths) {
        try {
            // Retrieve the existing item from the database
            Item existingItem = itemRepository.findByItemId(productId);
            if (existingItem == null) {
                throw new RuntimeException("Item not found with id: " + productId);
            }

            // Log before updating the item
            System.out.println("Updating item with id: " + productId);

            // Update the fields of the existing item with new values
            existingItem.setItemTitle(itemDTO.getProductName());
            existingItem.setItemCategory(itemDTO.getCategory());
            existingItem.setItemBrand(itemDTO.getBrand());
            existingItem.setItemPrice(itemDTO.getPrice());
            existingItem.setItemDetails(itemDTO.getDescription());
            existingItem.setItemQuantity(itemDTO.getQuantity());
            existingItem.setItemOffers(itemDTO.getOffers());

            // Save the updated item to the database
            itemRepository.save(existingItem);

            for (String imagePath : uploadedImagePaths) {
                // Clean the image path to remove square brackets
                String cleanedImagePath = imagePath.replaceAll("\\[|\\]", "");
                System.out.println("Saving image path: " + cleanedImagePath);

                ItemImages itemImage = new ItemImages();
                itemImage.setItem(existingItem);
                itemImage.setImagePath(cleanedImagePath);
                itemImagesRepository.save(itemImage);
            }

            System.out.println("Item updated successfully.");

        } catch (Exception e) {
            // Handle error
            System.err.println("Error editing item: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteItem(Long productId) {
        try {
            // Retrieve the item from the database
            Item item = itemRepository.findByItemId(productId);
            if (item == null) {
                throw new RuntimeException("Item not found with id: " + productId);
            }

            // Delete associated images from the database
            List<ItemImages> itemImages = itemImagesRepository.findByItemItemId(productId);
            itemImagesRepository.deleteAll(itemImages);

            // Delete the item from the database
            itemRepository.delete(item);

            // Delete associated images from the file system
            for (ItemImages image : itemImages) {
                String imageNameWithSpecialChars = image.getImagePath(); // Get the image name from the database
                // Remove square brackets [] from the image name
                // Remove double quotations from the image name
                String imageName = imageNameWithSpecialChars.replaceAll("\"", "");
                String imagePath = "src/main/resources/static/images/" + imageName; // Construct full image path
                File file = new File(imagePath);
                System.out.println("Attempting to delete image: " + imagePath); // Log deletion attempt
                if (file.exists()) {
                    if (file.delete()) {
                        System.out.println("Deleted image: " + imagePath); // Log successful deletion
                    } else {
                        System.out.println("Failed to delete image: " + imagePath); // Log deletion failure
                    }
                } else {
                    System.out.println("Image not found: " + imagePath); // Log image not found
                }
            }

        } catch (Exception e) {
            // Handle error
            System.err.println("Error deleting item: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

}
