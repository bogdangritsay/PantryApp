package com.uktech.pantry.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Pantry")
@Table(name = "pantries")
@Data
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pantryId;


    @OneToMany(targetEntity = PantryItems.class, mappedBy = "pantry")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PantryItems> productDetails = new HashSet<PantryItems>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Enumerated(EnumType.STRING)
    private PantryStatus status;

    public Pantry() {

    }

    public Pantry(Set<PantryItems> productDetails, User user) {
        this.productDetails = productDetails;
        this.user = user;
    }

    public Boolean isEmpty() {
        return productDetails.isEmpty();
    }

    public Set<PantryItems> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Set<PantryItems> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Pantry{" +
                "pantryId=" + pantryId +
                ", productDetails=" + productDetails.size() +
                ", user=" + user.getUsername() +
                ", status=" + status.name() +
                '}';
    }
}
