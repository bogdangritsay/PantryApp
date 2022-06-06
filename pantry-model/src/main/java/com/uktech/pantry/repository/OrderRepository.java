package com.uktech.pantry.repository;

import com.uktech.pantry.domain.Order;
import com.uktech.pantry.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(Long orderId);

    List<Order> findOrderByOrderStatusAndUserId(OrderStatus orderStatus, long userId);

}
