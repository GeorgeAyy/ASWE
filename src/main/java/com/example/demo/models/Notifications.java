package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "notification_text", nullable = false)
    private String notificationText;

    @Column(name = "notification_date", nullable = false)
    private Timestamp notificationDate;

    // i also want to add a column for title of the notification

    @Column(name = "notification_title", nullable = false)
    private String notificationTitle;

    public Notifications(Long notificationId, User user, String notificationText, Timestamp notificationDate,
            String notificationTitle) {
        this.notificationId = notificationId;
        this.user = user;
        this.notificationText = notificationText;
        this.notificationDate = notificationDate;
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationTitle() {
        return this.notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public Notifications notificationTitle(String notificationTitle) {
        setNotificationTitle(notificationTitle);
        return this;
    }

    // Add getters and setters

    public Notifications() {
    }

    public Long getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotificationText() {
        return this.notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public Timestamp getNotificationDate() {
        return this.notificationDate;
    }

    public void setNotificationDate(Timestamp notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Notifications notificationId(Long notificationId) {
        setNotificationId(notificationId);
        return this;
    }

    public Notifications user(User user) {
        setUser(user);
        return this;
    }

    public Notifications notificationText(String notificationText) {
        setNotificationText(notificationText);
        return this;
    }

    public Notifications notificationDate(Timestamp notificationDate) {
        setNotificationDate(notificationDate);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Notifications)) {
            return false;
        }
        Notifications notifications = (Notifications) o;
        return Objects.equals(notificationId, notifications.notificationId) && Objects.equals(user, notifications.user)
                && Objects.equals(notificationText, notifications.notificationText)
                && Objects.equals(notificationDate, notifications.notificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, user, notificationText, notificationDate);
    }

    @Override
    public String toString() {
        return "{" +
                " notificationId='" + getNotificationId() + "'" +
                ", user='" + getUser() + "'" +
                ", notificationText='" + getNotificationText() + "'" +
                ", notificationDate='" + getNotificationDate() + "'" +
                "}";
    }

}