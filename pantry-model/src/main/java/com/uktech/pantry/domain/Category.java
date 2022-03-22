package com.uktech.pantry.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Category> childCategories = new HashSet<>();

    public Category() {}

}
