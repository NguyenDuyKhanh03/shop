package com.example.shop.service;

import com.example.shop.models.Images;
import com.example.shop.models.dto.ImagesDto;

import java.util.List;

public interface ImagesService {
    List<ImagesDto> getImagesByProductId(Long productId);
}
