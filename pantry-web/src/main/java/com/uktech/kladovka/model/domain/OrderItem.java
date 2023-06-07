package com.uktech.kladovka.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Data

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName ="id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    private Integer orderAmount; //количество штук для определенного продукта в определенном ордере

    private Double totalPrice; //цена определенного продукта в определенном ордере

    private LocalDateTime prevOrderTime;
    private LocalDateTime currentOrderTime;

    public OrderItem() {

    }

    public OrderItem(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public OrderItem(Order order, Product product, int orderAmount, double totalPrice) {
        this.order = order;
        this.product = product;
        this.orderAmount = orderAmount;
        this.totalPrice = totalPrice;
    }

}

