package com.example.demo.controllers;


import java.util.ArrayList;
import java.util.List;

// import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;




@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private UserService userService;

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
        session.setAttribute("username", dbUser.getUser_id());
        return new RedirectView("/");}
        else{
            return new RedirectView("/auth/login");
        }
    }

    @GetMapping("/signup")
    public ModelAndView signup(@ModelAttribute UserDTO userDTO,Model model ) {
        ModelAndView mav = new ModelAndView("signup.html");
        model.addAttribute("userDTO", userDTO);
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
    public ModelAndView signup(@Valid @ModelAttribute UserDTO userDTO, BindingResult result) {
        
       if(userService.existEmail(userDTO.getEmail())){
        result.addError(new FieldError("userDTO", "email", "Email address already in use"));
       }

        if(userDTO.getUserPassword()!=null && userDTO.getCpassword()!=null){
            if(!userDTO.getUserPassword().equals(userDTO.getCpassword())){
                result.addError(new FieldError("userDTO", "cpassword", "Password must match"));
            }
        }

        if (result.hasErrors()) {
            System.out.println(result.hasErrors());
           return new ModelAndView("signup.html");
        }
        userService.saveUser(userDTO);
      return new ModelAndView("redirect:/auth/login");
}
}