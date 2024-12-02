package com.example.AllProject.Controller;

import com.example.AllProject.Model.Food;
import com.example.AllProject.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService service;
    @GetMapping("/enter")
    public String enterFoodItem(Model model){
        model.addAttribute("food",new Food());
        return "./html/addFood";
    }
    @PostMapping("/enter")
    public String foodEntered(@ModelAttribute Food food,Model mode){
        service.save(food);
        List<Food> foodItems =service.searchAllFood();
        mode.addAttribute("foodItems",foodItems);
        return "./html/AdminHomePage";
    }
    @PostMapping("/delete/{id}")
    public String deleteFood(@PathVariable Integer id,Model mode){
        service.deleteById(id);
        return "redirect:/users/admin";
    }
}
