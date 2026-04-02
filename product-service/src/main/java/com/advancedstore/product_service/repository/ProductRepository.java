package com.simplestore.product_service.repository;

import com.simplestore.product_service.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    Product findByName(String name);
    // More complex queries here
}