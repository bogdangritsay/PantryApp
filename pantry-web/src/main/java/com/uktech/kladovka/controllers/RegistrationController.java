package com.uktech.kladovka.controllers;

import com.uktech.kladovka.service.pantry.UserService;
import com.uktech.pantry.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String Registration()
    {
        return "registration";

    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model)
    {
        if (!userService.addUser(user)) {
            model.put("message", "user already exists");
            return "/registration";
        }
        return "redirect:/login";
    }
}
