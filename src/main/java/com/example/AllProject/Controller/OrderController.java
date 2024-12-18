package com.example.AllProject.Controller;

import com.example.AllProject.Model.Cart;
import com.example.AllProject.Model.Food;
import com.example.AllProject.Model.Order;
import com.example.AllProject.Model.User;
import com.example.AllProject.Service.CartService;
import com.example.AllProject.Service.FoodService;
import com.example.AllProject.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private FoodService foodService;

    @PostMapping("/action/{id}")
    public String action(@PathVariable Integer id){
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()){
            order.get().setStatus("Delivered");
            orderService.save(order.get());
            return "redirect:/users/admin";
        }else{
            return "redirect:/users/login";
        }
    }


    @GetMapping("/pay")
    public String payment(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/login";
        }
        List<Cart> cartItems = cartService.getCartItems(user);
        for (Cart cartItem : cartItems) {
            Food foodItem = cartItem.getFood();
            Optional<Food> optionalFood = foodService.searchById(foodItem.getId());

            if (optionalFood.isPresent()) {
                Food food = optionalFood.get();
                int availableQuantity = food.getQuantity();
                int orderedQuantity = cartItem.getQuantity();

                if (availableQuantity >= orderedQuantity) {
                    orderService.createOrder(user.getId(), foodItem.getId(), orderedQuantity);
                } else {
                    return "redirect:/cart/";
                }
                cartService.removeFromCart(cartItem.getId());
            } else {
                return "redirect:/cart/";
            }
        }
        return "redirect:/users/user";
    }


}