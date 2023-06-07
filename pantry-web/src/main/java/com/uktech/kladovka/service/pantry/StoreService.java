package com.uktech.kladovka.service.pantry;

import com.uktech.kladovka.model.domain.Store;
import com.uktech.kladovka.model.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public Iterable<Store> findAll() {
        return storeRepository.findAll();
    }

    public Store findById(Long id) {
        return storeRepository.findById(id).get();
    }
}
