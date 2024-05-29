package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Notifications;
import com.example.demo.models.User;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    List<Notifications> findByUserOrderByNotificationDateDesc(User user);
}
