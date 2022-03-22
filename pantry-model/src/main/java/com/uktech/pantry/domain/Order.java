package com.uktech.pantry.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Order")
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderName;
    private String orderDescription;
    private Long userId; //(fk)
    private String shopLink;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<OrderItem> orderDetails  = new HashSet<>();

    private Double totalOrderPrice;

    private LocalDateTime dateOfSubmit;
    // когда ордер переводим в деливеред для всех продуктов , перезаписуем это значение в orderDetails currentOrderTime
    // currentOrderTime предварительно сетим prevOrderTime
    private LocalDateTime deliveryDate;

    //когда ордер создаеться берем макс прайс из настроек
    private Double maxPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {

    }

    public Order(String orderName, String orderDescription, Long userId, OrderStatus orderStatus, Double maxPrice) {
        this.orderName = orderName;
        this.orderDescription = orderDescription;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.maxPrice = maxPrice;
    }

    public Order(Long userId, String shopLink, OrderStatus orderStatus) {
        this.userId = userId;
        this.shopLink = shopLink;
       this.orderStatus = orderStatus;
   }

   public String getDateOfSubmitInSimpleFormat() {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateOfSubmit.format(formatter);
   }

//    public Set<OrderItem> getOrderDetails() {
//        return orderDetails;
//    }
//
//    public void setOrderDetails(Set<OrderItem> orderDetails) {
//        this.orderDetails = orderDetails;
//    }
//
//    public Double getMaxPrice() {
//        return maxPrice;
//    }
//
//    public void setMaxPrice(Double maxPrice) {
//        if (maxPrice != null)
//            this.maxPrice = maxPrice;
//        else
//            this.maxPrice = 200000.00;
//    }
}
