package com.uktech.pantry.domain;

import javax.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

    private String address = "No address";
    private String email;
    private String defaultSite = "No site";
    private String phone;
    private Double defaultMaxPrice = 500D;

    public Settings() {}

    public Settings(User user, String address, String email, String defaultSite, String phone) {
        this.user = user;
        this.address = address;
        this.email = email;
        this.defaultSite = defaultSite;
        this.phone = phone;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getDefaultMaxPrice() {
        return defaultMaxPrice;
    }

    public void setDefaultMaxPrice(Double defaultMaxPrice) {
        this.defaultMaxPrice = defaultMaxPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDefaultSite() {
        return defaultSite;
    }

    public void setDefaultSite(String defaultSite) {
        this.defaultSite = defaultSite;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEmpty() {
        return address.isEmpty() && email.isEmpty() && defaultSite.isEmpty() && phone.isEmpty();
    }

}
