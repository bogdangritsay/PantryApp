/*
package com.uktech.kladovka.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pantries")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pantryId;

    @OneToMany(targetEntity=Product.class, mappedBy="pantry",cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    private Long userid;
    private Integer status;
    private String description;

    //TODO create pantry price can be null
    public Pantry() {

    }

    public Pantry(Set<Product> products, Long userid) {
        this.products = products;
        this.userid = userid;
    }

}
*/
