/*
package com.uktech.kladovka.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode(of = "orderId")
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String orderName;
    private String orderDescription;
    private Long userid; //(fk)
    private String shopLink;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetails> orderDetails  = new HashSet<>();

    private Double totalorderPrice = 0.0;
    // общее количество в штуках
    private Integer orderTotalAmount;

    private LocalDateTime dateofSubmit;
    // когда ордер переводим в деливеред для всех продуктов , перезаписуем это значение в orderDetails currentOrderTime
    // currentOrderTime предварительно сетим prevOrderTime
    private LocalDateTime deliveryDate;

    //когда ордер создаеться берем макс прайс из настроек
    private Double maxPrice;
    private Integer orderStatus; //active =1, indelivery = 2 completed =3

    public Order() {

    }

    public Order(Long userId, String shopLink, int orderStatus) {
        this.userid = userId;
        this.shopLink = shopLink;
        this.orderStatus = orderStatus;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        if (maxPrice != null)
            this.maxPrice = maxPrice;
        else
            this.maxPrice = 200000.00;
    }



}
*/
