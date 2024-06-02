package com.example.shop.repository;

import com.example.shop.models.Order;
import com.example.shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUser(User user);
    @Query(value = "SELECT o FROM Order o WHERE o.user.username = :username ORDER BY o.orderDate DESC")
    List<Order> findListOrderByUsername(String username);
}
