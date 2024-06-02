package com.example.shop.service;

import com.example.shop.models.Order;
import com.example.shop.models.ProductCart;
import com.example.shop.models.dto.OrderDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrderService {

    OrderDto createOrderFromCart(String username);

    List<ProductCart> getOrder(String username);

    OrderDto getOrder1(String username);

    List<OrderDto> getAllOrder(String username);
}
