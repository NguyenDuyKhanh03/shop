package com.example.shop.repository;

import com.example.shop.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images,Long> {
    @Query("SELECT i FROM Images i WHERE i.product.id = :productId")
    List<Images> findByProductId(Long productId);
}
