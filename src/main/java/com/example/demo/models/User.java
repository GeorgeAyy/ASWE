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
    private Long user_id;

    @Column(name = "user_Lname", nullable = false)
    private String user_Lname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_fname", nullable = false)
    private String user_fname;

    @Column(name = "user_address", nullable = false, columnDefinition = "TEXT")
    private String user_address;

    @Column(name = "user_user_isAdmin", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean user_isAdmin;


    public User() {
    }

    public User(Long user_id, String user_Lname, String email, String userPassword, String user_fname, String user_address, boolean user_isAdmin) {
        this.user_id = user_id;
        this.user_Lname = user_Lname;
        this.email = email;
        this.userPassword = userPassword;
        this.user_fname = user_fname;
        this.user_address = user_address;
        this.user_isAdmin = user_isAdmin;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_Lname() {
        return this.user_Lname;
    }

    public void setUser_Lname(String user_Lname) {
        this.user_Lname = user_Lname;
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

    public String getUser_fname() {
        return this.user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getUser_address() {
        return this.user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public boolean isUser_isAdmin() {
        return this.user_isAdmin;
    }

    public boolean getUser_isAdmin() {
        return this.user_isAdmin;
    }

    public void setUser_isAdmin(boolean user_isAdmin) {
        this.user_isAdmin = user_isAdmin;
    }

    public User user_id(Long user_id) {
        setUser_id(user_id);
        return this;
    }

    public User user_Lname(String user_Lname) {
        setUser_Lname(user_Lname);
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

    public User user_fname(String user_fname) {
        setUser_fname(user_fname);
        return this;
    }

    public User user_address(String user_address) {
        setUser_address(user_address);
        return this;
    }

    public User user_isAdmin(boolean user_isAdmin) {
        setUser_isAdmin(user_isAdmin);
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
        return Objects.equals(user_id, user.user_id) && Objects.equals(user_Lname, user.user_Lname) && Objects.equals(email, user.email) && Objects.equals(userPassword, user.userPassword) && Objects.equals(user_fname, user.user_fname) && Objects.equals(user_address, user.user_address) && user_isAdmin == user.user_isAdmin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, user_Lname, email, userPassword, user_fname, user_address, user_isAdmin);
    }

    @Override
    public String toString() {
        return "{" +
            " user_id='" + getUser_id() + "'" +
            ", user_Lname='" + getUser_Lname() + "'" +
            ", email='" + getEmail() + "'" +
            ", userPassword='" + getUserPassword() + "'" +
            ", user_fname='" + getUser_fname() + "'" +
            ", user_address='" + getUser_address() + "'" +
            ", user_isAdmin='" + isUser_isAdmin() + "'" +
            "}";
    }

    public boolean isUserAdmin() {
        return this.user_isAdmin;
    }
    
}
