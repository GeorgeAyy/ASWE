package com.example.demo.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "locations")
public class Location {
    
    @Id
    private Long id;

    private String branch_name;
    private String address;
    private String google_maps_url;

    public Location() {
    }

    public Location(Long id, String branch_name, String address, String google_maps_url) {
        this.id = id;
        this.branch_name = branch_name;
        this.address = address;
        this.google_maps_url = google_maps_url;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranch_name() {
        return this.branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoogle_maps_url() {
        return this.google_maps_url;
    }

    public void setGoogle_maps_url(String google_maps_url) {
        this.google_maps_url = google_maps_url;
    }

    public Location id(Long id) {
        setId(id);
        return this;
    }

    public Location branch_name(String branch_name) {
        setBranch_name(branch_name);
        return this;
    }

    public Location address(String address) {
        setAddress(address);
        return this;
    }

    public Location google_maps_url(String google_maps_url) {
        setGoogle_maps_url(google_maps_url);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(id, location.id) && Objects.equals(branch_name, location.branch_name) && Objects.equals(address, location.address) && Objects.equals(google_maps_url, location.google_maps_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branch_name, address, google_maps_url);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", branch_name='" + getBranch_name() + "'" +
            ", address='" + getAddress() + "'" +
            ", google_maps_url='" + getGoogle_maps_url() + "'" +
            "}";
    }

}