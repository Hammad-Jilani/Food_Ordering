package com.example.AllProject.Service;

import com.example.AllProject.Model.User;
import com.example.AllProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user){
        user.setRole("Customer");
        userRepository.save(user);
    }
    public Optional<User> findByUsername(String name){
        return userRepository.findByUsername(name);
    }
    public Optional<User> findByEmailAndPasswordAndRole(String email,String password,String role){
        return userRepository.findByEmailAndPasswordAndRole(email,password,role);
    }
}
