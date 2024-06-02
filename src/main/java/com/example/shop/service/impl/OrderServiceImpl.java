package com.example.shop.service.impl;

import com.example.shop.models.*;
import com.example.shop.models.dto.ImagesDto;
import com.example.shop.models.dto.OrderDto;
import com.example.shop.repository.*;
import com.example.shop.service.CartService;
import com.example.shop.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Transactional
    @Override
    public OrderDto createOrderFromCart(String username) {
        List<ProductCart> productCarts=cartService.getProductInCartItem(username);

        if(productCarts==null)
            throw new RuntimeException();

        Cart cart=cartRepository.findByUsername(username);
        User user=userRepository.findByUsername(username).get();


        Order order=new Order();
        order.setTotalAmount(cart.getTotal());
        order.setOrderDate(new Date());
        order.setUser(user);

        List<OrderDetail> orderDetails=new ArrayList<>();
        for(ProductCart productCart:productCarts){
            OrderDetail orderDetail=new OrderDetail();

            orderDetail.setQuantity(productCart.getQuantity());
            orderDetail.setPrice(productCart.getPrice());
            orderDetail.setDiscount(0);
            orderDetail.setOrder(order);

            Product product=productRepository.findById(productCart.getId()).get();
            orderDetail.setProduct(product);
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        cartService.clearCart(username);
        return mapper.map(order,OrderDto.class);
    }

    @Override
    public List<ProductCart> getOrder(String username) {
        List<Order> orders=orderRepository.findListOrderByUsername(username);
        if(orders.isEmpty())
            throw new RuntimeException();
        List<ProductCart> productCarts=new ArrayList<>();
        for(OrderDetail orderDetail:orders.get(0).getOrderDetails()){
            ProductCart productCart=new ProductCart();
            productCart.setId(orderDetail.getProduct().getId());
            productCart.setName(orderDetail.getProduct().getName());
            productCart.setImage(orderDetail.getProduct().getImage());
            productCart.setPrice(orderDetail.getProduct().getPrice());
            productCart.setQuantity(orderDetail.getQuantity());
            productCarts.add(productCart);
        }
        return productCarts;
    }

    @Override
    public OrderDto getOrder1(String username) {
        List<Order> orders=orderRepository.findListOrderByUsername(username);
        if(orders.isEmpty())
            throw new RuntimeException();
        Order order=orders.get(0);
        return mapper.map(order,OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrder(String username) {
        List<Order> orders=orderRepository.findListOrderByUsername(username);

        List<OrderDto> orderDtos=orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return orderDtos;
    }

    private OrderDto convertToDto(Order order) {
        return mapper.map(order, OrderDto.class);
    }


}
