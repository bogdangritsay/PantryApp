package com.uktech.kladovka.model.repository;

import com.uktech.kladovka.model.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
