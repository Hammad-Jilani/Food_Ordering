package com.example.AllProject.Repository;

import com.example.AllProject.Model.Cart;
import com.example.AllProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByUser(User user);
}
