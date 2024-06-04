package com.example.online_banking_system.controller;


import com.example.online_banking_system.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {


    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/login")
    public String showLoginForm(HttpServletRequest request, Model model){
        String errorMessage = (String) request.getSession().getAttribute("errorMessage");
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }else {
            model.addAttribute("errorMessage", "Invalid credentials");

        }
        return "login";
    }

    @GetMapping("/signup")
    public String showSignUpForm(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username, @RequestParam String password){
        userService.saveUser(username, password);
        return "redirect:/login?success=true";
    }


}
