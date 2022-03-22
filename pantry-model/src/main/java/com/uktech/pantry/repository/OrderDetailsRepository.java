package com.uktech.pantry.repository;

import com.uktech.pantry.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;


public interface OrderDetailsRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = "select oi.order_amount from order_items oi where oi.id = ?1", nativeQuery = true)
    Integer findOrderAmountById(Long id);

    @Query(value = "select oi.total_price from order_items oi where oi.id = ?1", nativeQuery = true)
    Double findTotalPriceById(Long id);

    OrderItem findOrderItemById(long id);

    Collection<OrderItem> findOrderItemByOrderId(Long id);






}
