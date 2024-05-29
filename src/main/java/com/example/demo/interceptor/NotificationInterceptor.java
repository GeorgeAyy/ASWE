package com.example.demo.interceptor;

import com.example.demo.models.User;
import com.example.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import com.example.demo.models.Notifications;

@Component
public class NotificationInterceptor implements HandlerInterceptor {

    @Autowired
    private NotificationService notificationService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null && modelAndView != null) {
                List<Notifications> notifications = notificationService.getNotificationsForUser(user);
                modelAndView.addObject("notifications", notifications);
            }
        }
    }
}
