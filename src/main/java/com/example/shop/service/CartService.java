package com.example.shop.service;



import com.example.shop.models.Cart;
import com.example.shop.models.ProductCart;
import com.example.shop.models.dto.CartDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartService {

    Cart findById(Long cartId);

    CartDto addProductToCart(Long productId, int quantity, String customerName);

    List<ProductCart> getProductInCartItem(String username);

    void updateProductInCart(String username, Long productId, int quantity);


    void clearCart(String username);
}
