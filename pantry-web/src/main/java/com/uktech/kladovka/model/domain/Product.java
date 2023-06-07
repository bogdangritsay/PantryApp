package com.uktech.kladovka.model.domain;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String name;

    @Column(columnDefinition="TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    private Double itemPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "store_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Store store;

    private URL linkToProduct;


    public Product(String name, String description, Category category, Double itemPrice, Set<OrderItem> orderItems, Store store, URL linkToProduct) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.itemPrice = itemPrice;
        this.orderItems = orderItems;
        this.store = store;
        this.linkToProduct = linkToProduct;
    }

    public Product() {}


}