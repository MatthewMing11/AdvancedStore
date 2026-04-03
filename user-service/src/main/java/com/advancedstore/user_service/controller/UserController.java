package com.advancedstore.user_service.controller;

import com.advancedstore.user_service.entity.User;
import com.advancedstore.user_service.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins={"http://localhost:3000"})
@RestController
@RequestMapping("/api/users")
public class UserController{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(
            user.getAmount(),
            user.getName(),
            user.getPrice()
        );
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/search")
    public ResponseEntity<User> getUserByName(@RequestParam String name){
        User user = userService.getUserByName(name);
        return user != null ? ResponseEntity.ok(user)
                                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()){
            updatedUser.setId(id);
            User user = userService.createUser(updatedUser.getAmount(),
            updatedUser.getName(),updatedUser.getPrice());
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        Optional<User> user=userService.getUserById(id);
        if(user.isPresent()){
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}