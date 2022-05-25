package com.uktech.kladovka.controllers;

import com.uktech.kladovka.service.pantry.OrderService;
import com.uktech.pantry.domain.Order;
import com.uktech.pantry.domain.OrderStatus;
import com.uktech.pantry.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class HistoryController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderHistory")
    public String viewHistory(@AuthenticationPrincipal User user, Model model)
    {
        Stream<Order> streamOfOrders = orderService.findAllOrders().stream();
        List<Order> ordersInHistory = streamOfOrders.
                filter(order -> OrderStatus.COMPLETED.equals(order.getOrderStatus())).
                collect(Collectors.toList());
        String shippingAddress = user.getAddress();
        
        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("orders", ordersInHistory);
        return "orderHistory";
    }
}
