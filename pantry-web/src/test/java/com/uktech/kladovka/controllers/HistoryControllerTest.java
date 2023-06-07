package com.uktech.kladovka.controllers;

import com.uktech.kladovka.model.domain.Order;
import com.uktech.kladovka.model.domain.OrderStatus;
import com.uktech.kladovka.model.domain.User;
import com.uktech.kladovka.service.pantry.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HistoryControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private User user;

    @Mock
    private Model model;

    @InjectMocks
    private HistoryController historyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewHistory() {
        // Arrange
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setOrderStatus(OrderStatus.COMPLETED);
        orders.add(order1);
        when(orderService.findAllOrders()).thenReturn(orders);
        when(user.getAddress()).thenReturn("123 Street");

        // Act
        String result = historyController.viewHistory(user, model);

        // Assert
        assertEquals("orderHistory", result);
        verify(model).addAttribute("shippingAddress", "123 Street");
        verify(model).addAttribute("orders", orders);
    }
}
