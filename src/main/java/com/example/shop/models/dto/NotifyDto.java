package com.example.shop.models.dto;

import com.example.shop.models.Notify;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotifyDto {
    private Long id;

    private String message;

    private String username;

    private LocalDateTime createdAt;
    private String type;

}
