package com.uktech.kladovka.controllers;

import com.uktech.kladovka.service.pantry.SettingsService;
import com.uktech.pantry.domain.Settings;
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
    private SettingsService settingsService;

    @GetMapping("/settings")
    public String openSettings(@AuthenticationPrincipal User user,
                               Model model)
    {
        Settings currentUserSetting = settingsService.getUserSettings(user.getId());
        model.addAttribute("setting", currentUserSetting);
        model.addAttribute("user" , user);
        return "settings";
    }

    @GetMapping("/settingEdit")
    public String editSettings(@AuthenticationPrincipal User user,
                               Model model)
    {
        Settings currentUserSetting = settingsService.getUserSettings(user.getId());
        model.addAttribute("setting", currentUserSetting);
        model.addAttribute("user" , user);
        return "settingsEdit";
    }

    @PostMapping("/settingEdit")
    public String updateSettings(@AuthenticationPrincipal User user,
                                 @RequestParam String address,
                                 @RequestParam String email,
                                 @RequestParam String defaultSite,
                                 @RequestParam String phone)
    {
        Settings userSettings = settingsService.getUserSettings(user.getId());

        setDefaultValueIfNull(address, userSettings.getAddress());
        setDefaultValueIfNull(email, userSettings.getEmail());
        setDefaultValueIfNull(defaultSite, userSettings.getDefaultSite());
        setDefaultValueIfNull(phone, userSettings.getPhone());

        userSettings.setAddress(address);
        userSettings.setEmail(email);
        userSettings.setDefaultSite(defaultSite);
        userSettings.setPhone(phone);
        userSettings.setDefaultMaxPrice(2000.00);
        settingsService.save(userSettings);

        return "redirect:/settings";
    }

    public void setDefaultValueIfNull(String updatedValue,  String deafaulValue) {
        if(StringUtils.isEmpty(updatedValue))
        {
           updatedValue = deafaulValue;
        }
    }
}
