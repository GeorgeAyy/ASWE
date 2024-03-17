package com.example.demo.dto;
import java.util.Objects;

public class UserDTO {
    private Long userId;
    private String userFname;
    private String userLname;
    private String email;
    private String userAddress;


    public UserDTO() {
    }

    public UserDTO(Long userId, String userFname, String userLname, String email, String userAddress) {
        this.userId = userId;
        this.userFname = userFname;
        this.userLname = userLname;
        this.email = email;
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
        return Objects.equals(userId, userDTO.userId) && Objects.equals(userFname, userDTO.userFname) && Objects.equals(userLname, userDTO.userLname) && Objects.equals(email, userDTO.email) && Objects.equals(userAddress, userDTO.userAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userFname, userLname, email, userAddress);
    }

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", userFname='" + getUserFname() + "'" +
            ", userLname='" + getUserLname() + "'" +
            ", email='" + getEmail() + "'" +
            ", userAddress='" + getUserAddress() + "'" +
            "}";
    }
    
}
