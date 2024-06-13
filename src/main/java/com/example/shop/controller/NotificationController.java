package com.example.shop.controller;

import com.example.shop.models.Notify;
import com.example.shop.models.dto.NotifyDto;
import com.example.shop.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping("/user/{username}")
    public List<NotifyDto> getNotificationsByUserId(@PathVariable("username") String username){
        List<NotifyDto> notifyDtos=notificationService.getNotificationByUser(username);
        return notifyDtos;
    }

    @GetMapping("/general")
    public List<NotifyDto> getGeneralNotifications() {
        List<NotifyDto> notifyDtos=notificationService.findByTypeOrderByCreatedAtDesc();
        return notifyDtos;
    }
}
