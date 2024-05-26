package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.User;

import java.util.List;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8081"; // Base URL for the user microservice

    public UserService() {
        this.restTemplate = new RestTemplate(); // Initialize a new RestTemplate instance
    }

    public void toggleAdmin(Long userId) {
        String url = baseUrl + "/User/" + userId + "/toggle-admin";
        logger.info("Calling toggleAdmin for userId: {}", userId);
        this.restTemplate.postForObject(url, null, Void.class);
    }

    public void updateUser(UserDTO userDTO) {
        String url = baseUrl + "/User/" + userDTO.getUserId();
        logger.info("Updating user with id: {}", userDTO.getUserId());
        this.restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(userDTO), Void.class);
    }

    public User getUserDetails(Long userId) {
        String url = baseUrl + "/User/" + userId;
        logger.info("Fetching user details for userId: {}", userId);
        return this.restTemplate.getForObject(url, User.class);
    }

    public void deleteUser(Long id) {
        String url = baseUrl + "/User/" + id;
        logger.info("Deleting user with id: {}", id);
        this.restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }

    public void saveUser(UserDTO userDTO) {
        String url = baseUrl + "/User/Registration";
        logger.info("Saving user with email: {}", userDTO.getEmail());
        this.restTemplate.postForObject(url, userDTO, User.class);
    }

    public boolean existEmail(String email) {
        String url = baseUrl + "/User/exists?email=" + email;
        logger.info("Checking if email exists: {}", email);
        return this.restTemplate.getForObject(url, Boolean.class);
    }

    public boolean getUser(String email, String password, HttpSession session) {
        String url = baseUrl + "/User/login?email=" + email + "&password=" + password;
        logger.info("Logging in user with email: {}", email);
        try {
            ResponseEntity<Boolean> response = this.restTemplate.exchange(url, HttpMethod.POST, null, Boolean.class);
            if (response.getBody() != null && response.getBody()) {
                User user = this.restTemplate.getForObject(baseUrl + "/User/by-email?email=" + email, User.class);
                session.setAttribute("user", user);
                logger.info("User logged in successfully: {}", email);
                return true;
            }
            logger.warn("User login failed: {}", email);
            return false;
        } catch (HttpClientErrorException e) {
            logger.error("Error logging in user with email: {}. Status code: {}, Response body: {}", email,
                    e.getStatusCode(), e.getResponseBodyAsString());
            return false;
        }
    }
}
