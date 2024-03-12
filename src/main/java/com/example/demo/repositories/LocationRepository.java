package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    // You can add custom queries if needed
}