package com.uktech.kladovka.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.uktech.kladovka.controllers.AdminController;
import com.uktech.kladovka.service.pantry.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class AdminControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private Model model;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserList() {
        // Arrange

        // Act
        String viewName = adminController.userList(model);

        // Assert
        assertEquals("admin", viewName);
        verify(model).addAttribute("allUsers", userService.findAll());
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        String action = "delete";

        // Act
        String viewName = adminController.deleteUser(userId, action, model);

        // Assert
        assertEquals("redirect:/admin", viewName);
        verify(userService).deleteUser(userId);
    }
}
