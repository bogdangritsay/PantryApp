package com.uktech.kladovka.controllers;

import com.uktech.kladovka.controllers.RegistrationController;
import com.uktech.kladovka.model.domain.User;
import com.uktech.kladovka.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegistrationControllerTest {

    @Mock
    private RegistrationService registrationService;

    @Mock
    private Model model;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterPage() {
        String result = registrationController.register(model);
        assertEquals("registration", result);
        verify(model, times(1)).addAttribute("userForm", new User());
    }

    @Test
    void testRegisterUser() {
        User user = new User();

        String result = registrationController.register(user);
        assertEquals("redirect:/login", result);
        verify(registrationService, times(1)).register(user);
    }

    @Test
    void testConfirmToken() {
        String token = "token";

        String result = registrationController.confirm(token, null);
        assertEquals("redirect:/login", result);
        verify(registrationService, times(1)).confirmToken(token);
    }
}
