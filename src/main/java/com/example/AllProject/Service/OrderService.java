package com.example.AllProject.Service;

import com.example.AllProject.Model.Food;
import com.example.AllProject.Model.Order;
import com.example.AllProject.Model.User;
import com.example.AllProject.Repository.FoodRepository;
import com.example.AllProject.Repository.OrderRepository;
import com.example.AllProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrder(Integer userId, Integer foodId, Integer quantity) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Food> foodOpt = foodRepository.findById(foodId);
        if (userOpt.isEmpty() || foodOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Food not found");
        }
        User user = userOpt.get();
        Food food = foodOpt.get();
        if (food.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for the selected food item");
        }
        food.setQuantity(food.getQuantity() - quantity);
        foodRepository.save(food);
        Order order = new Order();
        order.setFoodName(food.getName());
        order.setAddress(user.getAddress());
        order.setUser(user);
        order.setFood(food);
        order.setQuantity(quantity);
        order.setPrice(food.getPrice() * quantity);
        order.setStatus("Pending");
        order.setTime(LocalDateTime.now());
        order.setUserName(user.getUsername());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return orderRepository.findByUser(userOpt.get());
    }

    public Order updateOrderStatus(Integer orderId, String status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order order = orderOpt.get();
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }

        // Restore stock
        Order order = orderOpt.get();
        Food food = order.getFood();
        food.setQuantity(food.getQuantity() + order.getQuantity());
        foodRepository.save(food);

        orderRepository.delete(order);
    }
}
