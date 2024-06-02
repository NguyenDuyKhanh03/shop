package com.example.shop.controller;

import com.example.shop.models.Images;
import com.example.shop.models.dto.ImagesDto;
import com.example.shop.service.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImagesController {
    private final ImagesService imagesService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ImagesDto>> getImagesByProductId(@PathVariable Long productId) {
        List<ImagesDto> images = imagesService.getImagesByProductId(productId);
        if (images.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }


}
