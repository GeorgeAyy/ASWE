package com.example.demo.dto;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    private Long userId;
    @NotBlank(message = "enter the frist name")
    private String userFname;
    @NotBlank(message = "enter the last name")
    private String userLname;
    
    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Enter your password")
    @Length(min=8,message = "Password must be at least 8 characters")
    private String userPassword;

    @NotBlank(message = "Re-enter your password")
    private String cpassword;
    
    @NotBlank(message = "Enter your address")
    private String userAddress;



    public UserDTO() {
    }

    public UserDTO(Long userId, String userFname, String userLname, String email, String userPassword, String cpassword, String userAddress) {
        this.userId = userId;
        this.userFname = userFname;
        this.userLname = userLname;
        this.email = email;
        this.userPassword = userPassword;
        this.cpassword = cpassword;
        this.userAddress = userAddress;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFname() {
        return this.userFname;
    }

    public void setUserFname(String userFname) {
        this.userFname = userFname;
    }

    public String getUserLname() {
        return this.userLname;
    }

    public void setUserLname(String userLname) {
        this.userLname = userLname;
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

    public String getCpassword() {
        return this.cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public UserDTO userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public UserDTO userFname(String userFname) {
        setUserFname(userFname);
        return this;
    }

    public UserDTO userLname(String userLname) {
        setUserLname(userLname);
        return this;
    }

    public UserDTO email(String email) {
        setEmail(email);
        return this;
    }

    public UserDTO userPassword(String userPassword) {
        setUserPassword(userPassword);
        return this;
    }

    public UserDTO cpassword(String cpassword) {
        setCpassword(cpassword);
        return this;
    }

    public UserDTO userAddress(String userAddress) {
        setUserAddress(userAddress);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId) && Objects.equals(userFname, userDTO.userFname) && Objects.equals(userLname, userDTO.userLname) && Objects.equals(email, userDTO.email) && Objects.equals(userPassword, userDTO.userPassword) && Objects.equals(cpassword, userDTO.cpassword) && Objects.equals(userAddress, userDTO.userAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userFname, userLname, email, userPassword, cpassword, userAddress);
    }

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", userFname='" + getUserFname() + "'" +
            ", userLname='" + getUserLname() + "'" +
            ", email='" + getEmail() + "'" +
            ", userPassword='" + getUserPassword() + "'" +
            ", cpassword='" + getCpassword() + "'" +
            ", userAddress='" + getUserAddress() + "'" +
            "}";
    }
    
    
}
