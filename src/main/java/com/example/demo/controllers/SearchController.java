package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.ItemImagesRepository;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.dto.ItemWithImagesDTO;
import com.example.demo.models.Category;
import com.example.demo.models.Item;
import com.example.demo.models.ItemImages;

@Controller
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImagesRepository itemImagesRepository;

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "sort", required = false) String sort) {
        ModelAndView mav = new ModelAndView("productList.html");

        List<ItemWithImagesDTO> itemsWithImages = new ArrayList<>();
        Set<String> categories = new HashSet<>();
        // Search for items based on all provided parameters
        Iterable<Item> items = itemRepository.searchItems(title, category, minPrice, maxPrice);

        // Populate categories and add items to the list
        for (Item item : items) {
            categories.add(item.getItemCategory());
            List<ItemImages> cleanedItemImages = cleanItemImages(item.getItemId());
            ItemWithImagesDTO itemWithImagesDTO = new ItemWithImagesDTO(item, cleanedItemImages);
            itemsWithImages.add(itemWithImagesDTO);
        }

        // Filter items by price range if provided
        if (minPrice != null) {
            itemsWithImages = itemsWithImages.stream()
                    .filter(item -> item.getItem().getItemPrice() >= minPrice)
                    .collect(Collectors.toList());
        }
        if (maxPrice != null) {
            itemsWithImages = itemsWithImages.stream()
                    .filter(item -> item.getItem().getItemPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }

        // Sort items based on the provided sort parameter
        if (sort != null) {
            switch (sort) {
                case "2": // Price: Low to High
                    Collections.sort(itemsWithImages, Comparator.comparingDouble(
                            item -> item.getItem().getItemPrice()));
                    break;
                case "3": // Price: High to Low
                    Collections.sort(itemsWithImages, Comparator.comparingDouble(
                            item -> item.getItem().getItemPrice()));
                    Collections.reverse(itemsWithImages);
                    break;
                case "4": // Highest Offers
                    Collections.sort(itemsWithImages, Comparator.comparingDouble(
                            item -> item.getItem().getItemOffers()));
                    Collections.reverse(itemsWithImages); // Sorting from highest to lowest offer
                    break;
                default:
                    // No sorting needed for other cases
                    break;
            }
        }
        System.out.println("Category: " + category);
        mav.addObject("products", itemsWithImages);
        mav.addObject("categories", categories);
        mav.addObject("category", category);

        return mav;
    }

    private List<ItemImages> cleanItemImages(Long itemId) {
        Iterable<ItemImages> itemImages = itemImagesRepository.findByItemItemId(itemId);
        List<ItemImages> cleanedItemImages = new ArrayList<>();
        for (ItemImages image : itemImages) {
            String cleanedImagePath = image.getImagePath().replace("\"", "");
            image.setImagePath(cleanedImagePath);
            cleanedItemImages.add(image);
        }
        return cleanedItemImages;
    }

}
