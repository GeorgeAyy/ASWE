package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_Lname", nullable = false)
    private String userLastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_fname", nullable = false)
    private String userFirstName;

    @Column(name = "user_address", nullable = false, columnDefinition = "TEXT")
    private String userAddress;

    @Column(name = "user_isAdmin", nullable = false)
    private boolean isAdmin;


    public User() {
    }

    public User(Long userId, String userLastName, String email, String userPassword, String userFirstName, String userAddress, boolean isAdmin) {
        this.userId = userId;
        this.userLastName = userLastName;
        this.email = email;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userAddress = userAddress;
        this.isAdmin = isAdmin;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLastName() {
        return this.userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isIsAdmin() {
        return this.isAdmin;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public User userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public User userLastName(String userLastName) {
        setUserLastName(userLastName);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User userPassword(String userPassword) {
        setUserPassword(userPassword);
        return this;
    }

    public User userFirstName(String userFirstName) {
        setUserFirstName(userFirstName);
        return this;
    }

    public User userAddress(String userAddress) {
        setUserAddress(userAddress);
        return this;
    }

    public User isAdmin(boolean isAdmin) {
        setIsAdmin(isAdmin);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userLastName, user.userLastName) && Objects.equals(email, user.email) && Objects.equals(userPassword, user.userPassword) && Objects.equals(userFirstName, user.userFirstName) && Objects.equals(userAddress, user.userAddress) && isAdmin == user.isAdmin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userLastName, email, userPassword, userFirstName, userAddress, isAdmin);
    }

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", userLastName='" + getUserLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", userPassword='" + getUserPassword() + "'" +
            ", userFirstName='" + getUserFirstName() + "'" +
            ", userAddress='" + getUserAddress() + "'" +
            ", isAdmin='" + isIsAdmin() + "'" +
            "}";
    }    

}
