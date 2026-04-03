package com.advancedstore.user_service.service;

import com.advancedstore.user_service.entity.User;
import com.advancedstore.user_service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User createUser(int amount, String name, float price){
        User user = new User(amount,name,price);
        return userRepository.save(user);
    }
    public User getUserByName(String name){
        return userRepository.findByName(name);
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}