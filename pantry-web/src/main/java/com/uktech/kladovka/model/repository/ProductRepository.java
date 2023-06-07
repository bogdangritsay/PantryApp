package com.uktech.kladovka.model.repository;



import com.uktech.kladovka.model.domain.Product;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameLike(String name);

    Optional<Product> findById(Integer id);

}
