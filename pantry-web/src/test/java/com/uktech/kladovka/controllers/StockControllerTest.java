package com.uktech.kladovka.controllers;

import com.uktech.kladovka.model.domain.PantryItems;
import com.uktech.kladovka.model.domain.Product;
import com.uktech.kladovka.model.domain.User;
import com.uktech.kladovka.service.pantry.PantryService;
import com.uktech.kladovka.service.pantry.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StockControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private PantryService pantryService;

    @Mock
    private User user;

    @Mock
    private Model model;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMainPage() {
        // Arrange
        String filter = "testFilter";
        List<PantryItems> pantryItemsList = new ArrayList<>();
        when(pantryService.filterByProductName(filter)).thenReturn(pantryItemsList);

        // Act
        String result = stockController.mainPage(user, filter, model);

        // Assert
        assertEquals("pantrylist", result);
        verify(pantryService).filterByProductName(filter);
        verify(model).addAttribute("products", pantryItemsList);
        verify(model).addAttribute("filter", filter);
        verify(model).addAttribute("isActive", "active");
    }

    @Test
    void testGetOrderProductById() {
        // Arrange
        Integer id = 1;
        String expectedViewName = "viewproduct";

        // Act
        String result = stockController.getOrderProductById(model, id);

        // Assert
        assertEquals(expectedViewName, result);
        // Add assertions for any additional behavior
    }

    @Test
    void testAdd() {
        // Arrange
        String name = "Test Product";
        String amount = "2 liters";
        int pantryAmount = 5;
        double price = 10.99;
        Product product = new Product(name, null, null, price, null, null, null);

        // Act
        String result = stockController.add(user, name, amount, pantryAmount, price, model);

        // Assert
        assertEquals("pantrylist", result);
        verify(pantryService).addProductToPantry(product, user);
        // Add assertions for any additional behavior
    }
}
