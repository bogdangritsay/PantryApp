package com.uktech.kladovka.model.repository;


import com.uktech.kladovka.model.domain.Pantry;
import com.uktech.kladovka.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PantryRepository extends JpaRepository<Pantry, Long> {

    Pantry findPantryByStatus(int status);

    Pantry findPantryByUser(User user);
}
