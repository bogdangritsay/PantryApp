package com.uktech.pantry.repository;

import com.uktech.pantry.domain.Order;
import com.uktech.pantry.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderById(Long orderId);

    List<Order> findOrderByOrderStatusAndUserId(OrderStatus orderStatus, long userId);

}
