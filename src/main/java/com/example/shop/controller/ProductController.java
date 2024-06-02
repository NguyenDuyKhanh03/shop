package com.example.shop.controller;

import com.example.shop.models.Product;
import com.example.shop.models.dto.ProductDto;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @RequestMapping(name = "/addProduct",method = RequestMethod.POST)
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }


    @GetMapping("/findProductById/{id}")
    public ProductDto findProductById(@PathVariable("id") Long id){
        ProductDto product=productService.findProductById(id);
        return product;
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return productService.findListProductByCategory(categoryId);
    }

    @GetMapping("/get-all")
    public List<ProductDto> getAllProducts(){
        return productService.getAll();
    }

    @GetMapping("/category/{categoryId}/{limit}")
    public List<ProductDto> getProductsByCategoryIdLimitCount(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("limit") int limit
    ) {
        return productService.findListProductByCategoryLimit(categoryId,limit);
    }
}
