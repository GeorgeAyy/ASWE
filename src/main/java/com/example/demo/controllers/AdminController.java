package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ItemService;
import com.example.demo.services.UserService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
        ModelAndView mav = new ModelAndView("admin_templates/products.html");
        mav.addObject("title", "Products");
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
public void createItem(@RequestParam("uploadedImagePaths") List<String> uploadedImagePaths,
        @ModelAttribute ItemDTO itemDTO) {
    try {
        itemService.createItem(itemDTO, uploadedImagePaths);
        // return "redirect:/admin/products";
    } catch (Exception e) {
        // Handle error
        System.err.println("Error creating item: " + e.getMessage());
        e.printStackTrace();
        // return "redirect:/admin/products";
    }
}

}
