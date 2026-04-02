package com.simplestore.product_service.controller;

import com.simplestore.product_service.entity.Product;
import com.simplestore.product_service.service.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins={"http://localhost:3000"})
@RestController
@RequestMapping("/api/products")
public class ProductController{
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdProduct = productService.createProduct(
            product.getAmount(),
            product.getName(),
            product.getPrice()
        );
        return new ResponseEntity<>(createdProduct,HttpStatus.CREATED);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/search")
    public ResponseEntity<Product> getProductByName(@RequestParam String name){
        Product product = productService.getProductByName(name);
        return product != null ? ResponseEntity.ok(product)
                                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct){
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()){
            updatedProduct.setId(id);
            Product product = productService.createProduct(updatedProduct.getAmount(),
            updatedProduct.getName(),updatedProduct.getPrice());
            return ResponseEntity.ok(product);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        Optional<Product> product=productService.getProductById(id);
        if(product.isPresent()){
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}