package com.uktech.kladovka.controllers;

import com.uktech.kladovka.service.pantry.UserService;
import com.uktech.pantry.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SettingsController {

    @Autowired
    private UserService userService;


    @GetMapping("/settings")
    public String openSettings(@AuthenticationPrincipal User user,
                               Model model) {
        model.addAttribute("user" , user);
        return "settings";
    }

    @GetMapping("/settingEdit")
    public String editSettings(@AuthenticationPrincipal User user,
                               Model model) {
        model.addAttribute("user" , user);
        return "settingsEdit";
    }

    @PostMapping("/settingEdit")
    public String updateSettings(@AuthenticationPrincipal User user,
                                 @RequestParam String address,
                                 @RequestParam String email,
                                 @RequestParam String defaultSite,
                                 @RequestParam String phone) {
        user.setAddress(address);
        user.setEmail(email);
        user.setDefaultSite(StringUtils.isEmpty(defaultSite) ? "No sites" : defaultSite);
        user.setPhone(phone);
        userService.saveUser(user);

        return "redirect:/settings";
    }

}
