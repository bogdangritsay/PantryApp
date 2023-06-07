package com.uktech.kladovka.model.repository;

import com.uktech.kladovka.model.domain.Order;
import com.uktech.kladovka.model.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(Long orderId);

    List<Order> findOrderByOrderStatusAndUserId(OrderStatus orderStatus, long userId);

}
