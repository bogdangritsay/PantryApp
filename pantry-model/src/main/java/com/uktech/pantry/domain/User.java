package com.uktech.pantry.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean locked = false;
    private Boolean enabled = false;

    // User custom settings
    private String firstName;
    private String lastName;
    private String middleName;
    private String address = "No address";
    private String email;
    private String defaultSite = "No site";
    private String phone;
    private Double defaultMaxPrice = 500D;
    @Transient
    private String passwordConfirm;
    @OneToMany(targetEntity = Pantry.class, mappedBy = "user")
    private Set<Pantry> pantries = new HashSet<>();

    public User(
            String firstName,
            String lastName,
            String middleName,
            String password,
            Role role,
            String address,
            String email,
            String defaultSite,
            String phone,
            String passwordConfirm,
            Set<Pantry> pantries
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.password = password;
        this.role = role;
        this.address = address;
        this.email = email;
        this.defaultSite = defaultSite;
        this.phone = phone;

        this.passwordConfirm = passwordConfirm;
        this.pantries = pantries;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public boolean isAdmin() {
        return "ADMIN".equals(role.name());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFullName() {
        String fullName = getLastName() + ", " + getFirstName();
        if (middleName != null) {
            fullName+= " " + middleName;
        }
        return fullName;
    }
}
