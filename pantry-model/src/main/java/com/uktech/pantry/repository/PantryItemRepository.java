package com.uktech.pantry.repository;

import com.uktech.pantry.domain.PantryItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PantryItemRepository extends JpaRepository<PantryItems, Long> {

    List<PantryItems> findPantryItemsByProductLike(String name);
}
