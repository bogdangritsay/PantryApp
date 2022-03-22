package com.uktech.pantry.repository;

import com.uktech.pantry.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
