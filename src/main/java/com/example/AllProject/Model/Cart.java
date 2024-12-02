package com.example.AllProject.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private Integer quantity;

    public Cart() {
    }

    public Cart(Integer id, User user, Food food, Integer quantity) {
        this.id = id;
        this.user = user;
        this.food = food;
        this.quantity = quantity;
    }

    public Cart(User user, Food food, Integer quantity) {
        this.user = user;
        this.food = food;
        this.quantity = quantity;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Food getFood() {
        return food;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
