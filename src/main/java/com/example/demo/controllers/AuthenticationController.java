package com.example.demo.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        return mav;
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView("signup.html");
        mav.addObject("signupRequest", new SignupRequest());
        return mav;
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        System.out.println("Login request received");

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Perform data validation
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        // Check if the user exists in the database
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        // Check if the password is valid
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getUserPassword())) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        // Create a session
        session.setAttribute("user", user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/changeAccount")
    public ResponseEntity<String> changeAccount(@RequestBody User userDetails) {
        System.out.println("Change Account request received");

        // Perform data validation
        if (userDetails.getUser_id() == null || userDetails.getUser_fname() == null || userDetails.getUser_Lname() == null || userDetails.getUser_address() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        // Update the user details in the database
        try {
            userRepository.save(userDetails);
            return ResponseEntity.ok("User details updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user details");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        System.out.println("Signup request received");

        String firstname = signupRequest.getFirstname();
        String lastname = signupRequest.getLastname();
        String email = signupRequest.getEmail();
        String password = signupRequest.getPassword();
        String confirmpassword = signupRequest.getConfirmpassword();
        String address = signupRequest.getAddress();

        // Create an array to store error messages with field names
        List<String> errors = new ArrayList<>();

        // Perform data validation
        if (firstname == null || firstname.isEmpty()) {
            errors.add("First name is required");
        }

        if (lastname == null || lastname.isEmpty()) {
            errors.add("Last name is required");
        }

        if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            errors.add("Invalid email");
        }

        if (password == null || password.length() < 8) {
            errors.add("Password must be at least 8 characters long");
        }

        if (!password.equals(confirmpassword)) {
            errors.add("Passwords do not match");
        }

        if (address == null || address.isEmpty()) {
            errors.add("Address is required");
        }

        // If there are errors, return the errors
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        // Hash the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        // Save the user to the database
        User newUser = new User(null, lastname, email, hashedPassword, firstname, address, false);
        userRepository.save(newUser);

        // Redirect to login page
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}