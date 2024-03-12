package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ItemImages;

public interface ItemImagesRepository extends JpaRepository<ItemImages, Long>{
    
}
