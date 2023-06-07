package com.uktech.kladovka.controllers;

import com.uktech.kladovka.model.domain.User;
import com.uktech.kladovka.service.pantry.PantryService;
import com.uktech.kladovka.service.pantry.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PantryService pantryService;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testUserList() {
        // Arrange
        String expectedViewName = "userList";

        // Act
        String result = userController.userList(model);

        // Assert
        assertEquals(expectedViewName, result);
        verify(userService).findAll();
    }

    @Test
    void testSaveUser() {
        // Arrange
        String role = "ROLE_ADMIN";
        User user = new User();
        Map<String, String> form = Collections.singletonMap("key", "value");
        String expectedRedirectUrl = "redirect:/user";

        // Act
        String result = userController.saveUser(role, user, form);

        // Assert
        assertEquals(expectedRedirectUrl, result);
    }

    @Test
    void testUserEdit() {
        // Arrange
        User user = new User();
        String expectedViewName = "userEdit";

        // Act
        String result = userController.userEdit(user, model);

        // Assert
        assertEquals(expectedViewName, result);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("roles", Collections.singleton(user.getRole()));
    }

    @Test
    void testGetProfile() {
        // Arrange
        User user = new User();
        String expectedViewName = "profile";

        // Act
        String result = userController.getProfile(model, user);

        // Assert
        assertEquals(expectedViewName, result);
        verify(model).addAttribute("username", user.getUsername());
    }

    @Test
    void testUpdateProfile() {
        // Arrange
        User user = new User();
        String password = "newPassword";
        String expectedRedirectUrl = "redirect:/user/profile";

        // Act
        String result = userController.updateProfile(user, password);

        // Assert
        assertEquals(expectedRedirectUrl, result);
        verify(userService).updateProfile(user, password);
        // Add assertions for any additional behavior
    }
}
