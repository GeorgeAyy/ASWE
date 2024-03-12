package com.example.demo.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    
    @Id
    private Long category_id;

    private String category_name;



    public Category() {
    }

    public Category(Long category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public Long getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return this.category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Category category_id(Long category_id) {
        setCategory_id(category_id);
        return this;
    }

    public Category category_name(String category_name) {
        setCategory_name(category_name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category categoryModel = (Category) o;
        return Objects.equals(category_id, categoryModel.category_id) && Objects.equals(category_name, categoryModel.category_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, category_name);
    }

    @Override
    public String toString() {
        return "{" +
            " category_id='" + getCategory_id() + "'" +
            ", category_name='" + getCategory_name() + "'" +
            "}";
    }
}