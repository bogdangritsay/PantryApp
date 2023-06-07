package com.uktech.kladovka.controllers;

import com.uktech.kladovka.controllers.MainController;
import com.uktech.kladovka.model.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainControllerTest {


    @Mock
    private User user;

    @Mock
    private Model model;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testGreeting() {

        String result = mainController.greeting(user, model);

        assertEquals("greeting", result);
        verifyZeroInteractions(user);
        verifyZeroInteractions(model);
    }
}
