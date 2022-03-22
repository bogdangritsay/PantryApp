package com.uktech.pantry.repository;


import com.uktech.pantry.domain.Pantry;
import com.uktech.pantry.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PantryRepository extends JpaRepository<Pantry, Long> {

    Pantry findPantryByStatus(int status);

    Pantry findPantryByUser(User user);
}
