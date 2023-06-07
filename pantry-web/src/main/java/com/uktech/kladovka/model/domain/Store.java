package com.uktech.kladovka.model.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private URL linkToStore;

    @OneToMany(mappedBy = "store")
    @ToString.Exclude
    private Set<Product> products = new HashSet<>();


    public Store() {
    }

}
