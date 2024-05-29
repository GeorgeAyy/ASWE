package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.ItemImages;

public interface ItemImagesRepository extends JpaRepository<ItemImages, Long> {
    List<ItemImages> findByItemItemId(Long itemId);

    @Query("SELECT ii.imagePath FROM ItemImages ii WHERE ii.item.itemId = ?1")
    List<String> findImagePathsByItemId(Long itemId);

    @Query("SELECT i FROM ItemImages i WHERE i.imagePath = ?1")
    List<ItemImages> findByImagePath(String imagePath);

}
