package com.advancedstore.order_service.service;

import com.advancedstore.order_service.entity.Order;
import com.advancedstore.order_service.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService{
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    public Order createOrder(int amount, String name, float price){
        Order order = new Order(amount,name,price);
        return orderRepository.save(order);
    }
    public Order getOrderByName(String name){
        return orderRepository.findByName(name);
    }
    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}