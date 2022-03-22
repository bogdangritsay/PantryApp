package com.uktech.pantry.repository;



import com.uktech.pantry.domain.Product;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameLike(String name);

    Optional<Product> findById(Integer id);

}
