package com.uktech.kladovka.controllers;

import com.uktech.kladovka.service.pantry.OrderService;
import com.uktech.kladovka.service.pantry.PantryService;
import com.uktech.kladovka.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PantryService pantryService;

    @GetMapping("/")
    public String greeting(@AuthenticationPrincipal User user, Model model) {
        return "greeting";
    }
}