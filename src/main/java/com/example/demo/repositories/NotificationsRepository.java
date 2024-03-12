package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications, Long>{
    
}
