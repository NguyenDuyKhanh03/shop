package com.example.shop.service.impl;

import com.example.shop.models.Notify;
import com.example.shop.models.Order;
import com.example.shop.models.dto.NotifyDto;
import com.example.shop.models.dto.OrderDto;
import com.example.shop.repository.NotifyRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotifyRepository notifyRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    @Override
    public List<NotifyDto> getNotificationByUser(String username) {
        List<Notify> notifies=notifyRepository.findNotifiesByUsernameOrderByCreatedAtDesc(username);
        return notifies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotifyDto> findByTypeOrderByCreatedAtDesc() {
        List<Notify> notifies=notifyRepository.findNotifiesByTypeOrderByCreatedAtDesc(Notify.NotificationType.GENERAL);

        return notifies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private NotifyDto convertToDto(Notify notify) {
        return mapper.map(notify, NotifyDto.class);
    }
}
