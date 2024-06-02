package com.example.shop.controller;

import com.example.shop.models.Category;
import com.example.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public void createCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }
}
