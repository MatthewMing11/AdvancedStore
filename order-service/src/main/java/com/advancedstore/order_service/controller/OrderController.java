package com.advancedstore.order_service.controller;

import com.advancedstore.order_service.entity.Order;
import com.advancedstore.order_service.service.OrderService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins={"http://localhost:3000"})
@RestController
@RequestMapping("/api/orders")
public class OrderController{
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order createdOrder = orderService.createOrder(
            order.getAmount(),
            order.getName(),
            order.getPrice()
        );
        return new ResponseEntity<>(createdOrder,HttpStatus.CREATED);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/search")
    public ResponseEntity<Order> getOrderByName(@RequestParam String name){
        Order order = orderService.getOrderByName(name);
        return order != null ? ResponseEntity.ok(order)
                                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder){
        Optional<Order> existingOrder = orderService.getOrderById(id);
        if (existingOrder.isPresent()){
            updatedOrder.setId(id);
            Order order = orderService.createOrder(updatedOrder.getAmount(),
            updatedOrder.getName(),updatedOrder.getPrice());
            return ResponseEntity.ok(order);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        Optional<Order> order=orderService.getOrderById(id);
        if(order.isPresent()){
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}