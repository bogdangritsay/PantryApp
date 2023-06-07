package com.uktech.kladovka.controllers;

import com.uktech.kladovka.model.domain.*;
import com.uktech.kladovka.service.pantry.OrderItemService;
import com.uktech.kladovka.service.pantry.OrderService;
import com.uktech.kladovka.service.pantry.ProductService;
import com.uktech.kladovka.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderItemService orderItemService;

    @Mock
    private User user;

    @Mock
    private Model model;

    @InjectMocks
    private OrderController orderController;

    public OrderControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddOrder() {
        String result = orderController.addOrder(model);
        assertEquals("order/createOrder", result);
    }

    @Test
    void testAddOrderWithUser() {
        Order order = mock(Order.class);
        when(orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId())).thenReturn(Arrays.asList(order));

        String result = orderController.addOrder(user, model);

        assertEquals("redirect:/currentorder", result);
        verify(orderService, times(1)).findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId());
    }

    @Test
    void testAddProductToOrder() {
        List<Product> products = List.of(mock(Product.class));
        Order currentOrder = mock(Order.class);

        when(productRepository.findByNameLike(anyString())).thenReturn(products);
        when(orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId())).thenReturn(List.of(currentOrder));

        String result = orderController.addProductToOrder(user, "name", 10, 5.0, model);

        assertEquals("redirect:/currentorder", result);
        verify(orderService, times(1)).addProductToOrder(currentOrder, products, user, 10, 5.0);
    }

    @Test
    void testRemoveProduct() {
        OrderItem orderItem = mock(OrderItem.class);
        Order order = mock(Order.class);

        when(orderItemService.findOrderItemById(anyLong())).thenReturn(orderItem);
        when(orderItem.getOrder()).thenReturn(order);


        String result = orderController.removeProduct(user, 1L, model);

        assertEquals("redirect:/currentorder", result);
        verify(orderItemService, times(1)).deleteOrderItem(orderItem);
        verify(orderService, times(1)).updateOrderPrice(order);
    }

    @Test
    void testGetOrderProductById() {
        Order activeOrder = mock(Order.class);
        Product product = mock(Product.class);

        when(orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId())).thenReturn(List.of(activeOrder));
        when(orderService.editProductOnOrder(anyInt(), any(Order.class))).thenReturn(product);

        String result = orderController.getOrderProductById(user, model, 1);

        assertEquals("viewproduct", result);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProduct() {
        Product product = mock(Product.class);
        when(productService.findById(anyLong())).thenReturn(product);

        String result = orderController.editProduct(model, 1L);

        assertEquals("editProduct", result);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testSubmitEditedProduct() {
        Product product = mock(Product.class);
        when(productService.findById(anyLong())).thenReturn(product);

        String result = orderController.submitEditedProduct(1L, "10.0", "5");

        assertEquals("redirect:/currentorder", result);
        verify(productService, times(1)).updateProduct(product, "10.0", "5");
    }

    @Test
    void testCurrentOrderPage() {
        Order activeOrder = mock(Order.class);
        when(orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId())).thenReturn(List.of(activeOrder));

        String result = orderController.currentOrderPage(user, "", model);

        assertEquals("currentorder", result);
        verify(model, times(1)).addAttribute("order", activeOrder);
        verify(model, times(1)).addAttribute("items", activeOrder.getOrderDetails());
        verify(model, times(1)).addAttribute("filter", "");
        verify(model, times(1)).addAttribute("isActive", "active");
    }

    @Test
    void testSubmitOrder() {
        Order activeOrder = mock(Order.class);
        when(orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId())).thenReturn(List.of(activeOrder));

        String result = orderController.submitOrder(user, model);

        assertEquals("redirect:/indelivery", result);
        verify(orderService, times(1)).submitOrder(activeOrder, user);
    }
}
