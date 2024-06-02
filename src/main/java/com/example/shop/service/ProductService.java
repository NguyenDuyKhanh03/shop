package com.example.shop.service;

import com.example.shop.models.Product;
import com.example.shop.models.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    ProductDto findProductById(Long id);

    List<ProductDto> findListProductByCategory(Long idCate);

    List<ProductDto> getAll();

    List<ProductDto> findListProductByCategoryLimit(Long categoryId, int limit);
}
