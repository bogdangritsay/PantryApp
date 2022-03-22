package com.uktech.kladovka.controllers;

import com.uktech.kladovka.service.pantry.PantryService;
import com.uktech.kladovka.service.pantry.UserService;
import com.uktech.pantry.domain.Role;
import com.uktech.pantry.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PantryService pantryService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userList(Model model)
    {
        model.addAttribute("users" , userService.findAll());
        return "userList";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveUser( @RequestParam String username,
            @RequestParam("userid") User user,
                            @RequestParam Map<String, String> form)
    {

        userService.saveUser(user, username, form);
        int count = pantryService.countPantries();
        if(count == 0) {
            pantryService.createDefaultPantry(user);
        }
        return "redirect:/user";

    }

    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEdit(@PathVariable User user,
                           Model model )
    {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username", user.getUsername());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                String password)
    {
        userService.updateProfile(user, password);
        return "redirect:/user/profile";
    }
}
