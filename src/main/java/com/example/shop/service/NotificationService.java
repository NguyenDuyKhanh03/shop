package com.example.shop.service;

import com.example.shop.models.dto.NotifyDto;

import java.util.List;

public interface NotificationService {
    List<NotifyDto> getNotificationByUser(String username);

    List<NotifyDto> findByTypeOrderByCreatedAtDesc();
}
