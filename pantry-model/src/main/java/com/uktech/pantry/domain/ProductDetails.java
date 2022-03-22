package com.uktech.pantry.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class ProductDetails {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;
    private Integer itemsCount;

    public ProductDetails() {
    }

    public ProductDetails(Product product, Integer itemsCount) {
        this.product = product;
        this.itemsCount = itemsCount;
    }

}
