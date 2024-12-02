package com.example.AllProject.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status;
    private double price;
    private Integer quantity;
    private String foodName;
    private LocalDateTime time;
    private String userName;


    public Order(String userName,Integer order_id,LocalDateTime time, String address, String foodName, Food food, User user, String status, double price, Integer quantity) {
        this.order_id = order_id;
        this.time=time;
        this.food = food;
        this.user = user;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.foodName=foodName;
        this.address=address;
        this.userName=userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getFoodName() {
        return foodName;
    }
    private String address;

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Order() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public Food getFood() {
        return food;
    }

    public User getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
