package com.example.shop.controller;

import com.example.shop.models.ProductCart;
import com.example.shop.models.dto.OrderDto;
import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestParam String username) {
        OrderDto orderDto = orderService.createOrderFromCart(username);
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/get-order-detail")
    public ResponseEntity<List<ProductCart>> getOrder(@RequestParam String username){
        List<ProductCart> productCarts=orderService.getOrder(username);
        return ResponseEntity.ok(productCarts);
    }

    @GetMapping("/get-order")
    public ResponseEntity<OrderDto> getOrder1(@RequestParam String username){
        OrderDto orderDto=orderService.getOrder1(username);
        return ResponseEntity.ok(orderDto);
    }

//    @GetMapping("/get-all-order")
//    public ResponseEntity<List<OrderDto>> getAllOrder(@RequestParam String username){
//        List<OrderDto> orderDtos=orderService.getAllOrder(username);
//        return ResponseEntity.ok(orderDtos);
//    }
//
//    @GetMapping("/get-order-detail")
//    public ResponseEntity<List<OrderDto>> getAllOrder(@RequestParam String username,@RequestParam Long id){
//        List<OrderDto> orderDtos=orderService.getAllOrder(username);
//        return ResponseEntity.ok(orderDtos);
//    }
}
