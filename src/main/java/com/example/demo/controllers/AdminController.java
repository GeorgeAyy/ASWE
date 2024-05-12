package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.models.Item;
import com.example.demo.models.ItemImages;
import com.example.demo.models.User;
import com.example.demo.repositories.ItemImagesRepository;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ItemService;
import com.example.demo.services.UserService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.nio.file.Path;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";
    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImagesRepository itemImageRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("admin_templates/dashboard.html");
        mav.addObject("title", "Dashboard");
        return mav;
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashboard() {
        ModelAndView mav = new ModelAndView("admin_templates/dashboard.html");
        mav.addObject("title", "Dashboard");
        return mav;
    }

    @GetMapping("/orders")
    public ModelAndView getOrders() {
        ModelAndView mav = new ModelAndView("admin_templates/orders.html");
        mav.addObject("title", "Orders");
        return mav;
    }

    @GetMapping("/products")
    public ModelAndView getProducts() {
        // Assuming you have a service or repository to fetch the products
        List<Item> products = itemRepository.findAll(); // Fetch products from your data source

        // Create a new ModelAndView object and set the view name
        ModelAndView mav = new ModelAndView("admin_templates/products");

        // Add the products list as an attribute to the ModelAndView
        mav.addObject("products", products);

        // You can also add other attributes if needed
        mav.addObject("title", "Products");

        // Return the ModelAndView object
        return mav;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {

        ModelAndView mav = new ModelAndView("admin_templates/users.html");
        mav.addObject("title", "Users");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }

    @GetMapping("/users/toggle_admin/{id}")
    public String toggleAdmin(@PathVariable Long id) {
        userService.toggleAdmin(id);
        return "redirect:/admin/users"; // Redirect to the users page
    }

    @PostMapping("/editUser")
    public String editUser(@RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(userDTO);
            System.out.println("User updated successfully");
            return "redirect:/admin/users"; // Redirect to users page after successful update
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            // Handle error
            return "error"; // Redirect to error page
        }
    }

    @PostMapping("/getUserDetails")
    @ResponseBody
    public User getUserDetails(@RequestParam Long userId) {
        try {
            User user = userService.getUserDetails(userId);
            // Assuming you have implemented the getUserDetails method in UserService
            return user;
        } catch (Exception e) {
            System.err.println("Error fetching user details: " + e.getMessage());
            // Handle error
            return null;
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/upload")
    @ResponseBody
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                // Save the uploaded file to the specified directory
                String imagesDir = "src/main/resources/static/images/";
                Path imagePath = Path.of(imagesDir, file.getOriginalFilename());
                Files.createDirectories(imagePath.getParent());
                Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File uploaded successfully");
            } else {
                System.out.println("No file received");
            }
        } catch (IOException e) {
            System.err.println("Failed to upload file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @PostMapping("/createProduct")
    public String createItem(@RequestParam("uploadedImagePaths") List<String> uploadedImagePaths,
            @ModelAttribute ItemDTO itemDTO) {
        try {
            itemService.createItem(itemDTO, uploadedImagePaths);
            return "redirect:/admin/products";
            // return "redirect:/admin/products";
        } catch (Exception e) {
            // Handle error
            System.err.println("Error creating item: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/admin/products";
            // return "redirect:/admin/products";
        }
    }

    @PostMapping("/getProductDetails")
    @ResponseBody
    public Map<String, Object> getProductDetails(@RequestParam Long productId) {
        try {
            // Fetch product details by ID
            Optional<Item> optionalItem = itemRepository.findById(productId);
            if (optionalItem.isPresent()) {
                Item item = optionalItem.get();
                // Create a map to hold product details and image paths
                Map<String, Object> response = new HashMap<>();
                // Set product details
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setProductName(item.getItemTitle());
                itemDTO.setCategory(item.getItemCategory());
                itemDTO.setBrand(item.getItemBrand());
                itemDTO.setPrice(item.getItemPrice());
                itemDTO.setDescription(item.getItemDetails());
                itemDTO.setQuantity(item.getItemQuantity());
                itemDTO.setOffers(item.getItemOffers());
                response.put("product", itemDTO);

                // Fetch all images from the repository
                List<ItemImages> allImages = itemImageRepository.findAll();
                // Filter images for the given item
                List<String> imagePaths = new ArrayList<>();
                for (ItemImages img : allImages) {
                    if (img.getItem().equals(item)) {
                        // Extract filename from JSON array
                        String imagePath = img.getImagePath();
                        imagePaths.add(imagePath);

                    }
                }
                System.out.println("Found " + imagePaths.size() + " image(s) for product ID: " + productId);
                response.put("images", imagePaths);
                return response;
            } else {
                // Product not found
                return Collections.singletonMap("error", "Product not found");
            }
        } catch (Exception e) {
            System.err.println("Error fetching product details: " + e.getMessage());
            // Handle error
            return Collections.singletonMap("error", "Failed to fetch product details");
        }
    }

    @PostMapping("/editProduct")
    public String editItem(@RequestParam("editUploadedImagePaths") List<String> uploadedImagePaths,
            @ModelAttribute ItemDTO itemDTO, @RequestParam Long productId) {
        try {
            // Log before calling the editItem service method
            System.out.println("Editing item with id: " + productId);

            itemService.editItem(productId, itemDTO, uploadedImagePaths);
            return "redirect:/admin/products";
        } catch (Exception e) {

            return "redirect:/admin/products";
            // return "redirect:/admin/products";
        }
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean deleteImage(@RequestParam String filename) {
        try {
            // Adjust the search criteria for the image path

            // Delete the file from the file system
            File file = new File(UPLOAD_DIR + filename);
            String imagePath = "\"" + filename + "\"";
            if (file.delete()) {
                System.out.println("File deleted successfully");

                // Delete the image references from the database
                List<ItemImages> images = itemImageRepository.findByImagePath(imagePath);
                itemImageRepository.deleteAll(images);

                return true;
            } else {
                System.out.println("Failed to delete the file" + filename);
                // Delete the image references from the database
                List<ItemImages> images = itemImageRepository.findByImagePath(imagePath);
                itemImageRepository.deleteAll(images);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error deleting file: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
