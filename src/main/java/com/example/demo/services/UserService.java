package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void toggleAdmin(Long userId) {
        // Fetch user by ID
        User user = userRepository.findById(userId)
                                   .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Toggle admin status
        user.setUser_isAdmin(!user.isUserAdmin());

        // Save the updated user
        userRepository.save(user);
    }

    public void updateUser(UserDTO userDTO) {
        // Validate user data if necessary
        
        // Assuming you have a UserRepository or another data access layer to interact with the database
        User userToUpdate = userRepository.findById(userDTO.getUserId())
                                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user fields
        userToUpdate.setUser_fname(userDTO.getUserFname());
        userToUpdate.setUser_Lname(userDTO.getUserLname());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setUser_address(userDTO.getUserAddress());

        // Save the updated user back to the database
        userRepository.save(userToUpdate);
    }

    public User getUserDetails(Long userId) {
        // Fetch user details from the database
        return userRepository.findById(userId)
                             .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void deleteUser(Long id) {
        
        // Fetch user by ID
        User user = userRepository.findById(id)
                                 .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Delete the user
        userRepository.delete(user);
    }
}
