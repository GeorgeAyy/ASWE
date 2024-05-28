package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.WishlistDTO;
import com.example.demo.models.ItemImages;
import com.example.demo.models.User;
import com.example.demo.models.Wishlist;
import com.example.demo.repositories.ItemImagesRepository;
import com.example.demo.repositories.WishlistRepository;

@Service
public class WishlistService {

    private static final Logger logger = LoggerFactory.getLogger(WishlistService.class);

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ItemImagesRepository itemImagesRepository;

    public void addWishlistItem(Wishlist wishlist) {
        logger.info("Adding item ID: {} to wishlist for user ID: {}", wishlist.getItem().getItemId(),
                wishlist.getUser().getUser_id());
        wishlistRepository.save(wishlist);
        logger.info("Item ID: {} added to wishlist for user ID: {}", wishlist.getItem().getItemId(),
                wishlist.getUser().getUser_id());
    }

    public List<WishlistDTO> getWishlistItems(User user) {
        logger.info("Fetching wishlist items for user ID: {}", user.getUser_id());
        return wishlistRepository.findByUser(user).stream()
                .map(this::convertToWishlistDTO)
                .collect(Collectors.toList());
    }

    private WishlistDTO convertToWishlistDTO(Wishlist wishlist) {
        List<String> imagePaths = itemImagesRepository.findByItemItemId(wishlist.getItem().getItemId()).stream()
                .map(ItemImages::getImagePath)
                .collect(Collectors.toList());

        return new WishlistDTO(
                wishlist.getWishlistId(),
                wishlist.getItem().getItemTitle(),
                wishlist.getItem().getItemCategory(),
                wishlist.getItem().getItemBrand(),
                wishlist.getItem().getItemPrice(),
                wishlist.getItem().getItemQuantity(),
                wishlist.getItem().getItemOffers(),
                wishlist.getItem().getItemDetails(),
                imagePaths);
    }

    public void removeWishlistItem(Long wishlistId) {
        logger.info("Removing wishlist item ID: {}", wishlistId);
        wishlistRepository.deleteById(wishlistId);
    }
}
