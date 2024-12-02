package com.example.AllProject.Controller;

import com.example.AllProject.Model.Cart;
import com.example.AllProject.Model.Food;
import com.example.AllProject.Model.User;
import com.example.AllProject.Service.CartService;
import com.example.AllProject.Service.FoodService;
import com.example.AllProject.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @GetMapping("/add/{foodId}")
    public String addToCart(@PathVariable Integer foodId,Model model){
        Optional<Food> food = foodService.searchById(foodId);
        if(food.isPresent()){
            model.addAttribute("food",food.get());
            return "./html/addCart";
        }else{
            return "redirect:/users/menu";
        }
    }
    // Add food to cart
    @PostMapping("/add/{foodId}")
    public String addToCart(HttpSession session, @RequestParam Integer quantity, @PathVariable Integer foodId, Model model) {
        User user = (User)session.getAttribute("currentUser");
        Cart cart = cartService.addToCart(user, foodId, quantity);
        if (cart != null) {
            model.addAttribute("message", "Food added to cart");
        } else {
            model.addAttribute("message", "Food is not available");
        }
        return "redirect:/users/menu";
    }

    @GetMapping("/")
    public String viewCart(Model model, HttpSession session) {
        User user = (User)session.getAttribute("currentUser");
        List<Cart> cartItems = cartService.getCartItems(user);
        model.addAttribute("cartItems", cartItems);
        return "./html/cart"; // Return cart.html view
    }

    @PostMapping("/remove/{cartId}")
    public String removeFromCart(@PathVariable Integer cartId, Model model) {
        cartService.removeFromCart(cartId);
        model.addAttribute("message", "Item removed from cart");
        return "redirect:/cart/";
    }
}
