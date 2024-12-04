package com.example.AllProject.Service;

import com.example.AllProject.Model.Cart;
import com.example.AllProject.Model.Food;
import com.example.AllProject.Model.User;
import com.example.AllProject.Repository.CartRepository;
import com.example.AllProject.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private CartRepository cartRepository;
    public Cart addToCart(User user, Integer foodId, Integer quantity) {
        Food food = foodRepository.findById(foodId).orElse(null);
        if (food != null && food.isAvailable()) {
            Cart cart = new Cart(user, food, quantity);
            return cartRepository.save(cart);
        }
        return null;
    }
    public List<Cart> getAllCardItems(){
        return cartRepository.findAll();
    }
    public List<Cart> getCartItems(User user) {
        return cartRepository.findByUser(user.getId());
    }

    public void removeFromCart(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    public List<Cart> getAllCartItems() {
        String sql = "select * from cart c join food f on c.food_id = f.id join user_table u ON c.user_id = u.user_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cart.class));
    }

}