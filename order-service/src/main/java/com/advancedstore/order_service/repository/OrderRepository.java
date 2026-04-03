package com.advancedstore.order_service.repository;

import com.advancedstore.order_service.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    Order findByName(String name);
    // More complex queries here
}