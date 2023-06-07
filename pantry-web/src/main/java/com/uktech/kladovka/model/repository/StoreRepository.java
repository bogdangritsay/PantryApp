package com.uktech.kladovka.model.repository;

import com.uktech.kladovka.model.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
