package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
        List<Item> findByItemCategory(String item_cat);
}
