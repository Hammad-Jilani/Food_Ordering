package com.example.AllProject.Repository;

import com.example.AllProject.Model.Order;
import com.example.AllProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByUser(User user);
    List<Order> findByUserId(Integer userId);
}
