/*
package com.uktech.kladovka.domain;


import com.uktech.pantry.domain.User;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String Description;
    private Integer category_id;
    private Integer link_id; // по нажатию сабмит на ордере перезаписываем этот параметр с shop_id на ордере

    private int pantryAmount;  //TODO значение для сохранения количества штук
    private String amount; // TODO обьем товара в литрах граммах изменить на стринг и поменять имя

    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private double price;

    @ManyToOne
    @JoinColumn(name="pantryId", referencedColumnName = "pantryId", updatable = false)
    private Pantry pantry;

    */
/*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private User owner;*//*


    @OneToMany(mappedBy = "product")
   private Set<OrderDetails> orderDetails  = new HashSet<>();

    @Column(columnDefinition = "integer default 0")
    private int distanceDays;

    @Column(columnDefinition = "integer default 0")
    private int pantryDayLeft;

    public Product() {}

    public Product(String name, String amount, int pantryAmount, double price */
/* ,User user*//*
 ) {
        this.name = name;
        this.amount = amount;
        this.pantryAmount = pantryAmount;
        this.price =price;
        //this.owner = user;

    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getPantryDayLeft() {
        return pantryDayLeft;
    }

    public void setPantryDayLeft(int pantryDayLeft) {
        this.pantryDayLeft = pantryDayLeft;
    }

    public int getPantryAmount() {
        return pantryAmount;
    }

    public void setPantryAmount(int pantryAmount) {
        this.pantryAmount = pantryAmount;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
        this.pantry = pantry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getLink_id() {
        return link_id;
    }

    public void setLink_id(Integer link_id) {
        this.link_id = link_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    */
/*
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }*//*


    public int getDistanceDays() {
        return distanceDays;
    }

    public void setDistanceDays(int distanceDays) {
        this.distanceDays = distanceDays;
    }

    */
/*
    public String getOwnerName()
    {
        return owner.getUsername();
    }
     *//*

}*/
