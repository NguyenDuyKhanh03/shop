package com.example.shop.service.impl;

import com.example.shop.models.*;
import com.example.shop.models.dto.CartDto;
import com.example.shop.models.dto.ProductDto;
import com.example.shop.repository.CartItemRepository;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository customerRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

//    @Override
//    public Cart findCartByCustomerId(String username) {
//        Optional<User> customer=customerRepository.findByUsername(username);
//        if(customer.isPresent()){
//            Cart cart=cartRepository.findByUserId(customer.get().getId());
//            if(cart==null){
//                cart=new Cart();
//                cart.setUser(customer.get());
//                cart.setCreateOn(LocalDateTime.now());
//                cart.setStatus("Create");
//                cartRepository.save(cart);
//            }
//            return cart;
//        }
//        return null;
//    }

    @Override
    public Cart findById(Long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart.get();
    }

    @Override
    public CartDto addProductToCart(Long productId, int quantity, String customerName) {
        Optional<User> customer = customerRepository.findByUsername(customerName);
        if (customer.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }

        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Product currentProduct = product.get();

        Cart userCart = cartRepository.findByUsername(customerName);
        if (userCart == null) {
            userCart = new Cart();
            userCart.setUsername(customerName);
            userCart.setTotal(0);
            userCart.setCreateOn(LocalDateTime.now());
            userCart.setStatus("Active");
            userCart.setCartItems(new ArrayList<>());

            CartItem cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            cartItem.setCart(userCart);
            userCart.getCartItems().add(cartItem);

            userCart.setTotal(userCart.getTotal() + (quantity * currentProduct.getPrice()));

            cartRepository.save(userCart);
        } else {
            Optional<CartItem> existingCartItem = userCart.getCartItems().stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst();

            if (existingCartItem.isPresent()) {
                CartItem cartItem = existingCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                userCart.setTotal(userCart.getTotal() + (quantity * currentProduct.getPrice()));
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setProductId(productId);
                cartItem.setQuantity(quantity);
                cartItem.setCart(userCart);
                userCart.getCartItems().add(cartItem);
                userCart.setTotal(userCart.getTotal() + (quantity * currentProduct.getPrice()));
            }

            cartRepository.save(userCart);
        }

        return mapper.map(userCart, CartDto.class);
    }

    @Override
    public List<ProductCart> getProductInCartItem(String username) {
        Optional<User> user=customerRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Cart cart=cartRepository.findByUsername(username);
        if(cart==null || cart.getCartItems().isEmpty())
            return Collections.EMPTY_LIST;

        List<ProductCart> productCarts=new ArrayList<>();
        for(CartItem cartItem:cart.getCartItems()){
            Product product=productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            ProductDto productDto=mapper.map(product,ProductDto.class);

            productDto.setQuantity(cartItem.getQuantity());
            ProductCart productCart=new ProductCart();

            productCart.setId(cartItem.getProductId());
            productCart.setName(productDto.getName());
            productCart.setQuantity(productDto.getQuantity());
            productCart.setImage(productDto.getImage());
            productCart.setPrice(productDto.getPrice());

            productCarts.add(productCart);
        }
        return productCarts;
    }

    @Override
    public void updateProductInCart(String username, Long productId, int quantity) {
        Optional<User> user=customerRepository.findByUsername(username);
        if(user.isEmpty())
            throw new RuntimeException("User not found");

        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty())
            throw new RuntimeException("Product not found");

        Cart cart=cartRepository.findByUsername(username);
        if(cart==null)
            throw new RuntimeException();


        Optional<CartItem> cartItemOpt = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            int newQuantity = cartItem.getQuantity() - quantity;

            if (newQuantity > 0) {
                cartItem.setQuantity(newQuantity);
                cart.setTotal(cart.getTotal()-product.get().getPrice()*quantity);
            } else {
                cart.getCartItems().remove(cartItem);
                cart.setTotal(cart.getTotal()-product.get().getPrice()*cartItem.getQuantity());
            }

            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Product not found in cart");
        }
    }

    @Transactional
    @Override
    public void clearCart(String username) {
        Optional<User> user = customerRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        Cart cart = cartRepository.findByUsername(username);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        cart.getCartItems().clear();
        cart.setTotal(0);
        cartRepository.save(cart);
    }


}
