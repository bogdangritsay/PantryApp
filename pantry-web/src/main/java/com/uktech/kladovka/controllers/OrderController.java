package com.uktech.kladovka.controllers;

import com.uktech.kladovka.service.pantry.OrderItemService;
import com.uktech.kladovka.service.pantry.OrderService;
import com.uktech.kladovka.service.pantry.ProductService;
import com.uktech.pantry.domain.*;
import com.uktech.pantry.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;


    //TODO  create button for new Order creation

    @GetMapping("/neworder")
    public String addOrder(Model model) {
        return "order/createOrder";
    }


    @PostMapping("/neworder")
    public String addOrder(@AuthenticationPrincipal User user,
                           Model model) {
        Order orderByOrderStatus = orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId()).stream().findFirst().get();

        if (orderByOrderStatus == null) {
            throw new UnsupportedOperationException("Order creation is not implemented.");
        } else {
            model.addAttribute("orderexist", "You already have active order");
        }

        return "redirect:/currentorder";
    }

    @PostMapping("/addProduct")
    public String addProductToOrder(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      @RequestParam  int amount,
                      @RequestParam double price,  Model model){

        //TODO should find all products which has
        // the same name and add ability to add selected
        List<Product> products = productRepository.findByNameLike(name);

        // TODO переделать на ИД
        Order currentOrder = orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId()).stream().findFirst().get();
        orderService.addProductToOrder(currentOrder, products,user,amount,price);

        model.addAttribute("order" , currentOrder);
        model.addAttribute("products", currentOrder);
        return "redirect:/currentorder";
    }

    //--------------------------------------------------------------------

    @RequestMapping("/currentorder/delete/{id}")
    public String removeProduct(@AuthenticationPrincipal User user, @PathVariable("id") Long id, Model model) {
        OrderItem orderItem = orderItemService.findOrderItemById(id);
        Order order = orderItem.getOrder();
        if (order.getUserId().equals(user.getId())) {
            orderItemService.deleteOrderItem(orderItem);
            orderService.updateOrderPrice(order);
        }

      return "redirect:/currentorder";

    }

    //TODO выгурзка по продукту когда последний раз заказывал , какая была цена , количество , когда был заказ
    @GetMapping(value = "/currentorder/pruduct/{id}")
    public String getOrderProductById(@AuthenticationPrincipal User user, Model model, @PathVariable Integer id) {
        Order orderByOrderStatus = orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId()).stream().findFirst().get();
        Product product = null;
        try {
            product = orderService.editProductOnOrder(id, orderByOrderStatus);
            model.addAttribute("allowDelete", true);
            model.addAttribute("isPantry", false);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
        model.addAttribute("product", product);
        return "viewproduct";
    }

    @GetMapping(value = "/currentorder/edit/{id}")
    public String editProduct(Model model, @PathVariable Long id) {
        Product editedProduct = productService.findById(id);

        model.addAttribute("product", editedProduct);
        return "editProduct";
    }

    @PostMapping(value = "/currentorder/edit/{id}")
    public String submitEditedProduct( @PathVariable Long id,
                                      @RequestParam  String orderPrice,
                                      @RequestParam String orderAmount )
    {
        Product editedProduct = productService.findById(id);
        if(null != editedProduct)
        {
            productService.updateProduct(editedProduct, orderPrice, orderAmount);
        }
        return "redirect:/currentorder";
    }
    @GetMapping("/currentorder")
    public String currentOrderPage(@AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "") String filter,
                           Model model) {
        //TODO currently we have only one active order, rewrite it for using several active orders
        Order activeOrder = orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId()).stream().findFirst().get();;

        Long activeOrderId = activeOrder.getId();
        log.info("Active productId : {} ", activeOrderId);
        Set<OrderItem> orderItems = activeOrder.getOrderDetails();

        model.addAttribute("order", activeOrder);
        model.addAttribute("items", orderItems);
        model.addAttribute("filter", filter);
        model.addAttribute("isActive", "active");

        return"currentorder";
    }


    @PostMapping("/submitorder")
    public String submitOrder(@AuthenticationPrincipal User user, Model model) {
        Order activeOrder = orderService.findActiveOrderOrCreateDefault(OrderStatus.ACTIVE, user.getId()).stream().findFirst().get();
        if (activeOrder != null) {
            orderService.submitOrder(activeOrder, user);
        }

        return "redirect:/indelivery";
    }

}
