package com.uktech.kladovka.service.pantry;


import com.uktech.kladovka.model.domain.Category;
import com.uktech.kladovka.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return  categoryRepository.getById(id);
    }
}
