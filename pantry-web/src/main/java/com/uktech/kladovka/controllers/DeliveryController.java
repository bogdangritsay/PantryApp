package com.uktech.kladovka.controllers;


import com.uktech.kladovka.service.pantry.*;
import com.uktech.pantry.domain.*;
import com.uktech.pantry.domain.utils.OrderByDateComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.TreeSet;

@Controller
public class DeliveryController {

    private final String errorMessage = "You don't have any order In Delivery!";

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private PantryService pantryService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/indelivery")
    public String getProductsInDelivery(@AuthenticationPrincipal User user, Model model) {
        List<Order> ordersInDelivery = orderService.findOrderByStatus(OrderStatus.IN_DELIVERY, user.getId());
        OrderByDateComparator comparator = new OrderByDateComparator();
        TreeSet<Order> orders = new TreeSet<>(comparator);
        orders.addAll(ordersInDelivery);

        if (ordersInDelivery != null && ordersInDelivery.size() != 0) {
            model.addAttribute("orders", orders);

            String shippingAddress = user.getAddress();
            if (shippingAddress == null)
                shippingAddress  = "No delivery address yet";

            model.addAttribute("shippingAddress", shippingAddress);
        } else {
            model.addAttribute("errorMessage", errorMessage );
        }

        return "indelivery";
    }

    @PostMapping("/delivered")
    public String delivered(@AuthenticationPrincipal User user, Model model) {/*
        Pantry pCurrentPantry = pantryService.getPCurrentPantry(user);
        Order orderInDelivery = orderService.findActiveOrder(OrderStatus.COMPLETED, user.getId());
        if(null != orderInDelivery)
        {
            deliveryService.deliveredProductsSaveInPantry(user, pCurrentPantry, orderInDelivery);
            deliveryService.moveOrderInDeliveryToHistory(orderInDelivery);

            model.addAttribute("products", pCurrentPantry.getProductDetails());
            return "redirect:/main";
        } else
        {
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("products", new HashSet<Product>());
            return "redirect:/indelivery";
        }*/
        return "redirect:/indelivery";
    }

}
