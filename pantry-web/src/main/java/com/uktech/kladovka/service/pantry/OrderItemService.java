package com.uktech.kladovka.service.pantry;



import com.uktech.kladovka.model.domain.Order;
import com.uktech.kladovka.model.domain.OrderItem;
import com.uktech.kladovka.model.domain.Product;
import com.uktech.kladovka.model.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class OrderItemService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    //TODO: commented by Bohdan Hrytsai for moving to new model
    public List<Product> getProductByOrderID(Long orderId) {
        Long longOrderId = orderId;
       // return orderDetailsRepository.findProductsByOrder(longOrderId);
        return null;
    }

    public void createNewEmptyOrderDetails(Order order) {

        OrderItem orderItem = new OrderItem(order, null);
        orderDetailsRepository.save(orderItem);
    }

    public void addOrderDetails(Order currentOrder, Product addedProduct, int amount, double price) {
        OrderItem orderProduct = new OrderItem(currentOrder, addedProduct, amount, price);
        orderDetailsRepository.save(orderProduct);
    }

    public double findOrderPriceById(Long id) {

        return orderDetailsRepository.findTotalPriceById(id);
    }

    public int findOrderAmountById(Long id) {
        return orderDetailsRepository.findOrderAmountById(id);
    }

    public OrderItem findOrderItemById(Long id) {
        return orderDetailsRepository.findOrderItemById(id);
    }

    public void setProducts(List<Product> products, Order order) {
        //TODO: set products for order
        /*
        Collection<OrderItem> orderDetailsByOrder = findOrderItemsByOrder(order);
        for (OrderItem orderItem : orderDetailsByOrder) {
            orderItem.setProduct(order);
        }
        orderDetailsRepository.saveAll(orderDetailsByOrder);*/
    }

    public Collection<OrderItem> findOrderItemsByOrder(Order order) {

        //TODO think about haw to handle this potential null pointer
        return orderDetailsRepository.findOrderItemByOrderId(order.getId());
    }

    public void addOrderItem(OrderItem orderItem) {
        orderDetailsRepository.save(orderItem);
    }

    public void deleteOrderItem(OrderItem orderItem) {
        orderDetailsRepository.delete(orderItem);
    }
}
