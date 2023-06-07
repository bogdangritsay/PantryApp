package com.uktech.kladovka.controllers;

import com.uktech.kladovka.controllers.SettingsController;
import com.uktech.kladovka.model.domain.User;
import com.uktech.kladovka.service.pantry.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SettingsControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private SettingsController settingsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testOpenSettings() {
        User user = new User();

        String result = settingsController.openSettings(user, model);
        assertEquals("settings", result);
        verify(model, times(1)).addAttribute("user", user);
    }

    @Test
    void testEditSettings() {
        User user = new User();

        String result = settingsController.editSettings(user, model);
        assertEquals("settingsEdit", result);
        verify(model, times(1)).addAttribute("user", user);
    }

    @Test
    void testUpdateSettings() {
        User user = new User();
        String address = "New Address";
        String email = "newemail@example.com";
        String defaultSite = "New Default Site";
        String phone = "1234567890";

        String result = settingsController.updateSettings(user, address, email, defaultSite, phone);
        assertEquals("redirect:/settings", result);
        user.setAddress(address);
        user.setEmail(email);
        user.setDefaultSite(StringUtils.isEmpty(defaultSite) ? "No sites" : defaultSite);
        user.setPhone(phone);
        verify(userService, times(1)).saveUser(user);
    }
}
