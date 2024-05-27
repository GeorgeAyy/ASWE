package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
        List<Item> findByItemCategory(String item_cat);

        Item findByItemId(Long itemId);

        List<Item> findByItemTitleContaining(String item_title);

        @Query("SELECT i FROM Item i " +
                        "WHERE (:title IS NULL OR i.itemTitle LIKE %:title%) " +
                        "AND (:category IS NULL OR i.itemCategory = :category) " +
                        "AND (:minPrice IS NULL OR i.itemPrice >= :minPrice) " +
                        "AND (:maxPrice IS NULL OR i.itemPrice <= :maxPrice)")
        Page<Item> searchItems(@Param("title") String title,
                        @Param("category") String category,
                        @Param("minPrice") Double minPrice,
                        @Param("maxPrice") Double maxPrice,
                        Pageable pageable);
}