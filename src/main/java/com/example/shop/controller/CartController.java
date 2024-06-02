package com.example.shop.controller;

import com.example.shop.models.ProductCart;
import com.example.shop.models.dto.CartDto;
import com.example.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add-product")
    public CartDto addProductToCart(@RequestParam String username,@RequestParam Long productId,@RequestParam int quantity){
        CartDto cartDto=cartService.addProductToCart(productId,quantity,username);
        return cartDto;
    }

    @GetMapping("/list-product")
    public List<ProductCart> getProductInCartItem(@RequestParam String username){
        return cartService.getProductInCartItem(username);
    }

    @PostMapping("/update-product")
    public void updateProductFromCart(@RequestParam String username,@RequestParam Long productId,@RequestParam int quantity){
        cartService.updateProductInCart(username,productId,quantity);
    }


}
