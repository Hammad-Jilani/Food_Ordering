package com.example.AllProject.Controller;

import com.example.AllProject.Model.Food;
import com.example.AllProject.Model.Order;
import com.example.AllProject.Model.User;
import com.example.AllProject.Service.FoodService;
import com.example.AllProject.Service.OrderService;
import com.example.AllProject.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLogin(){
        return "./html/landingPage";
    }
    @GetMapping("/login")
    public String showLoginPage(){
        return "./html/login";
    }

    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderService orderService;
    @PostMapping("/login")
    public String authenticate(HttpSession session, @RequestParam String email, @RequestParam String password, @RequestParam String role,Model model){
        Optional<User> optUser = userService.findByEmailAndPasswordAndRole(email,password,role);

        if (optUser.isPresent()){
            User user = optUser.get();
            session.setAttribute("currentUser",user);
            session.setAttribute("status","Logged in");
            if(role.equals("Customer")){
                List<Order> orders = orderService.getOrdersByUser(optUser.get().getId());
                session.setAttribute("orders",orders);
                return "./html/userHomePage";
            }
            else{
                List<Food> items = foodService.searchAllFood();
                List<Order> orders = orderService.getAllOrders();
                session.setAttribute("orders",orders);
                model.addAttribute("foodItems",items);
                return "./html/AdminHomePage";
            }
        }else{
            model.addAttribute("error","Incorrect Value");
            return "redirect:/users/login";
        }
    }


    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user",new User());
        return "./html/register";
    }
    @PostMapping("/register")
    public String savingUser(@ModelAttribute User user,Model model,HttpSession session){
        userService.save(user);
        session.setAttribute("currentUser",user);
        return "redirect:/users/login";
    }
    @GetMapping("/admin")
    public String admin(HttpSession session,Model model){
        User current = (User) session.getAttribute("currentUser");
        if(current!=null && current.getRole().equals("Owner")) {
            List<Food> foodItems =foodService.searchAllFood();
            List<Order> listOrder = orderService.getAllOrders();
            session.setAttribute("orders",listOrder);
            model.addAttribute("foodItems",foodItems);
            return "./html/AdminHomePage";
        }else{
            return "redirect:/users/login";
        }
    }

    @GetMapping("/user")
    public String user(HttpSession session,Model model){
        User current =(User)session.getAttribute("currentUser");
        if(current!=null && current.getRole().equals("Customer")) {
            List<Order> orders = orderService.getOrdersByUser(current.getId());
            session.setAttribute("orders",orders);
            return "./html/userHomePage";
        }else{
            return "redirect:/users/login";
        }
    }
    @GetMapping("/menu")
    public String menu(Model model){
        List<Food> list = foodService.searchAllFood();
        model.addAttribute("foodItems",list);
        return "./html/menu";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/users/login";
    }
}
