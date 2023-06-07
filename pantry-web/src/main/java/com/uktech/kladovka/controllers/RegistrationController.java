package com.uktech.kladovka.controllers;

import com.uktech.kladovka.service.RegistrationService;
import com.uktech.kladovka.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String register(@Valid User userForm) {
        registrationService.register(userForm);
        return "redirect:/login";
    }

    @GetMapping("/registration/confirm")
    public String confirm(@RequestParam("token") String token, HttpServletRequest request) {
        registrationService.confirmToken(token);
        return "redirect:/login";
    }
}
