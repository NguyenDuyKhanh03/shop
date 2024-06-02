package com.example.shop.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagesDto {
    private Long id;
    private String name;
    private String url;
    int relation_id;
    String type;
}
