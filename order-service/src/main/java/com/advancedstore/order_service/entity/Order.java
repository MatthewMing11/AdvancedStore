package com.advancedstore.order_service.entity;

import jakarta.persistence.*;

@Entity
public class Order{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private int amount;
    private String name;
    private float price;
    public Order() {}
    public Order(int amount, String name, float price){
        this.amount=amount;
        this.name=name;
        this.price=price;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public String getName(){
        return name;
    }
    public void setString(String name){
        this.name=name;
    }
    public float getPrice(){
        return price;
    }
    public void setPrice(float price){
        this.price=price;
    }
}