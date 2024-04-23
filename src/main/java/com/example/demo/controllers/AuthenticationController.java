package com.example.demo.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.repositories.UserRepository;

import jakarta.servlet.http.HttpSession;

import com.example.demo.models.User;




@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("/login")
    public RedirectView getuser(@RequestParam("email") String email,
    @RequestParam("userPassword") String userPassword, HttpSession session) {
        
        User dbUser= this.UserRepository.findByEmail(email);
        Boolean isPassword = BCrypt.checkpw(userPassword, dbUser.getUserPassword());
        if (isPassword) {
        session.setAttribute("username", dbUser.getUser_fname());
        return new RedirectView("/");}
        else{
            return new RedirectView("/auth/login");
        }
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView("signup.html");
        User user = new User();
        mav.addObject("signupRequest", user);
        return mav;
    }


    @GetMapping("/changeAccount")
    public ModelAndView changeAccount() {
        ModelAndView mav = new ModelAndView("account.html");
        return mav;
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
            UserRepository.save(userDetails);
            return ResponseEntity.ok("User details updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user details");
        }
    }
    @PostMapping("/signup")
    public RedirectView signup(@Valid @ModelAttribute User signupRequest, BindingResult result) {
        
        System.out.println("Signup request received");

     

        String firstname = signupRequest.getUser_fname();
        String lastname = signupRequest.getUser_Lname();
        String email = signupRequest.getEmail();
        String password = signupRequest.getUserPassword();
        String address = signupRequest.getUser_address();

        List<String> errors = new ArrayList<>();

        if (firstname == null || firstname.isEmpty()) {
            errors.add("First name is required");
            return new RedirectView("/auth/signup?error=firstNameError");
        }

        if (lastname == null || lastname.isEmpty()) {
            errors.add("Last name is required");
            return new RedirectView("/auth/signup?error=lastNameError");
        }

        if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            errors.add("Invalid email");
            return new RedirectView("/auth/signup?error=emailError");
        }

        if (password == null || password.length() < 8) {
            errors.add("Password must be at least 8 characters long");
            return new RedirectView("/auth/signup?error=passwordError");
        }

        if (address == null || address.isEmpty()) {
            errors.add("Address is required");
            return new RedirectView("/auth/signup?error=addressError");
        }
     
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        signupRequest.setUserPassword(encodedPassword);
        this.UserRepository.save(signupRequest);
        
        return new RedirectView("/auth/login");
    }
}