package com.example.AllProject.Controller;

import com.example.AllProject.Model.User;
import com.example.AllProject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLogin(){
        return "./html/login";
    }

    @PostMapping("/login")
    public String authenticate(@RequestParam String email,@RequestParam String password,Model model){
        Optional<User> optUser = userService.findByEmailAndPassword(email,password);
        if (optUser.isPresent()){
           return "./html/landingPage";
        }else{
            model.addAttribute("error","Incorrect Value");
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user",new User());
        return "./html/register";
    }
    @PostMapping("/register")
    public String savingUser(@ModelAttribute User user,Model model){
        userService.save(user);
        return "./html/landingPage";
    }

}
