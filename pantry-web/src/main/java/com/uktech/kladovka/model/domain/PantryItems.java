package com.uktech.kladovka.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table( name = "pantry_items")
public class PantryItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer itemsCount;

    @ManyToOne
    @JoinColumn(name = "pantry_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Pantry pantry;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    public PantryItems() {

    }

    public PantryItems(Product product, Integer itemsCount) {
        this.product = product;
        this.itemsCount = itemsCount;
    }

    public PantryItems(Integer itemsCount, Pantry pantry, Product product) {
        this.itemsCount = itemsCount;
        this.pantry = pantry;
        this.product = product;
    }

}
