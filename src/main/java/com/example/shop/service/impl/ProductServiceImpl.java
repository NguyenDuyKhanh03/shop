package com.example.shop.service.impl;

import com.example.shop.config.MapperConfig;
import com.example.shop.models.Product;
import com.example.shop.models.dto.ProductDto;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public ProductDto findProductById(Long id) {
        Optional<Product> product=productRepository.findById(id);
        ProductDto productDto=modelMapper.map(product.get(),ProductDto.class);
        return productDto;
    }

    @Override
    public List<ProductDto> findListProductByCategory(Long idCate) {
        List<Product> products=productRepository.findByCategoryId(idCate);

        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products=productRepository.findAll();

        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findListProductByCategoryLimit(Long categoryId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        Page<Product> page = productRepository.findByCategoryId(categoryId, pageable);
        return page.getContent().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
