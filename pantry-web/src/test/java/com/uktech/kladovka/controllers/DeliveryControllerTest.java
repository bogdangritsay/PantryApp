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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DeliveryControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private Model model;

    @InjectMocks
    private DeliveryController deliveryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testGetProductsInDeliveryWithOrders() {
        // Arrange
        User user = new User();
        user.setId(1L);
        List<Order> ordersInDelivery = new ArrayList<>();
        Order order1 = new Order();
        Order order2 = new Order();
        order1.setDateOfSubmit(LocalDateTime.now());
        order2.setDateOfSubmit(LocalDateTime.now());
        ordersInDelivery.add(order1);
        ordersInDelivery.add(order2);
        when(orderService.findOrderByStatus(OrderStatus.IN_DELIVERY, user.getId())).thenReturn(ordersInDelivery);

        // Act
        String viewName = deliveryController.getProductsInDelivery(user, model);

        // Assert
        assertEquals("indelivery", viewName);
        verify(model).addAttribute(eq("orders"), any());
        verify(model).addAttribute(eq("shippingAddress"), any());
    }

    @Test
    public void testGetProductsInDeliveryWithoutOrders() {
        // Arrange
        User user = new User();
        user.setId(1L);
        List<Order> ordersInDelivery = new ArrayList<>();
        when(orderService.findOrderByStatus(OrderStatus.IN_DELIVERY, user.getId())).thenReturn(ordersInDelivery);

        // Act
        String viewName = deliveryController.getProductsInDelivery(user, model);

        // Assert
        assertEquals("indelivery", viewName);
        verify(model).addAttribute("errorMessage", "You don't have any order In Delivery!");
    }

    @Test
    public void testDelivered() {
        // Arrange
        Long deliveredOrderId = 1L;
        User user = new User();
        Order deliveredOrder = new Order();
        when(orderService.findOrderById(deliveredOrderId)).thenReturn(deliveredOrder);

        // Act
        String viewName = deliveryController.delivered(deliveredOrderId, user, model);

        // Assert
        assertEquals("redirect:/indelivery", viewName);
        assertEquals(OrderStatus.COMPLETED, deliveredOrder.getOrderStatus());
        assertNotNull(deliveredOrder.getDeliveryDate());
        verify(orderService).saveOrder(deliveredOrder);
    }
}
