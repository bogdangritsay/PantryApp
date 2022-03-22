/*
package com.uktech.kladovka.repos;

import com.uktech.kladovka.domain.Order;
import com.uktech.kladovka.domain.OrderDetails;
import com.uktech.kladovka.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

   //@Query("SELECT od.product FROM order_details od WHERE od.order_id = ?1")
    List<Product> findProductByorder(Long id);

    int findOrderAmountByid(Long id);

    double findOrderPriceByid(Long id);


    Optional<OrderDetails> findByOrder( Order order);

}
*/
