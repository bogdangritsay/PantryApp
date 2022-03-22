/*
package com.uktech.kladovka.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
public class OrderDetails{

    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id")
    private Product product;
    private int orderAmount; //количество штук для определенного продукта в определенном ордере

    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private Double orderPrice; //цена определенного продукта в определенном ордере

    private LocalDateTime prevOrderTime;
    private LocalDateTime currentOrderTime;

    public OrderDetails() {
    }

    public OrderDetails(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public OrderDetails(Order order, Product product, int orderAmount, double orderPrice) {
        this.order = order;
        this.product = product;
        this.orderAmount = orderAmount;
        this.orderPrice = orderPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public LocalDateTime getPrevOrderTime() {
        return prevOrderTime;
    }

    public void setPrevOrderTime(LocalDateTime prevOrderTime) {
        this.prevOrderTime = prevOrderTime;
    }


    public LocalDateTime getCurrentOrderTime() {
        return currentOrderTime;
    }

    public void setCurrentOrderTime(LocalDateTime currentOrderTime) {
        this.currentOrderTime = currentOrderTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

*/
