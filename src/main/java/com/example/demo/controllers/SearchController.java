package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.ItemImagesRepository;
import com.example.demo.repositories.ItemRepository;

import jakarta.servlet.http.HttpSession;

import com.example.demo.dto.ItemWithImagesDTO;
import com.example.demo.models.Category;
import com.example.demo.models.Item;
import com.example.demo.models.ItemImages;
import com.example.demo.models.User;

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
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpSession session) {
        ModelAndView mav = new ModelAndView("productList.html");
        mav.addObject("user", (User) session.getAttribute("user"));

        Pageable pageable = PageRequest.of(page, size);
        Page<Item> itemsPage = itemRepository.searchItems(title, category, minPrice, maxPrice, pageable);

        List<ItemWithImagesDTO> itemsWithImages = new ArrayList<>();
        Set<String> categories = new HashSet<>();

        for (Item item : itemsPage) {
            categories.add(item.getItemCategory());
            List<ItemImages> cleanedItemImages = cleanItemImages(item.getItemId());
            ItemWithImagesDTO itemWithImagesDTO = new ItemWithImagesDTO(item, cleanedItemImages);
            itemsWithImages.add(itemWithImagesDTO);
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

        mav.addObject("products", itemsWithImages);
        mav.addObject("categories", categories);
        mav.addObject("category", category);
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", itemsPage.getTotalPages());
        mav.addObject("pageSize", size);

        return mav;
    }

    @GetMapping("/item/{id}")
    public ModelAndView getItemDetails(@PathVariable("id") Long id, HttpSession session) {
        ModelAndView mav = new ModelAndView("itemPage.html");
        mav.addObject("user", (User) session.getAttribute("user"));

        // Fetch product details using the ID
        Item item = itemRepository.findById(id).orElseThrow();
        List<ItemImages> itemImages = cleanItemImages(id);

        // Calculate the discounted price
        double discountedPrice = item.getItemPrice() - (item.getItemPrice() * item.getItemOffers() / 100);
        String formattedDiscountedPrice = String.format("%.2f", discountedPrice);

        // Add product details to the ModelAndView
        mav.addObject("item", item);
        mav.addObject("images", itemImages);
        mav.addObject("discountedPrice", formattedDiscountedPrice);

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
