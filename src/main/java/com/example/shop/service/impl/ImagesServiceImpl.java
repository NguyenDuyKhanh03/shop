package com.example.shop.service.impl;

import com.example.shop.models.Images;
import com.example.shop.models.Product;
import com.example.shop.models.dto.ImagesDto;
import com.example.shop.models.dto.ProductDto;
import com.example.shop.repository.ImagesRepository;
import com.example.shop.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImagesService {
    private final ImagesRepository imagesRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<ImagesDto> getImagesByProductId(Long productId) {
        List<Images> images=imagesRepository.findByProductId(productId);
        return images.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ImagesDto convertToDto(Images images) {
        return modelMapper.map(images, ImagesDto.class);
    }
}
