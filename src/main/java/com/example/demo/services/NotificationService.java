package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import com.example.demo.models.Notifications;
import com.example.demo.models.User;
import com.example.demo.repositories.NotificationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class NotificationService {

    @Autowired
    private NotificationsRepository notificationRepository;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void createNotification(User user, String title, String text) {
        Notifications notification = new Notifications();
        notification.setUser(user);
        notification.setNotificationTitle(title);
        notification.setNotificationText(text);
        notification.setNotificationDate(new Timestamp(System.currentTimeMillis()));
        notificationRepository.save(notification);
        logger.info("Created notification for user with ID: {}, title: {}, text: {}", user.getUser_id(), title, text);
    }

    public List<Notifications> getNotificationsForUser(User user) {
        logger.info("Retrieving notifications for user with ID: {}", user.getUser_id());
        List<Notifications> notifications = notificationRepository.findByUserOrderByNotificationDateDesc(user);
        logger.info("Retrieved {} notifications for user with ID: {}", notifications.size(), user.getUser_id());
        return notifications;
    }
}
