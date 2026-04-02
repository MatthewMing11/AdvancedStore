package com.advancedstore.product_service.service;

import com.advancedstore.product_service.entity.Product;
import com.advancedstore.product_service.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService{
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public Product createProduct(int amount, String name, float price){
        Product product = new Product(amount,name,price);
        return productRepository.save(product);
    }
    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}