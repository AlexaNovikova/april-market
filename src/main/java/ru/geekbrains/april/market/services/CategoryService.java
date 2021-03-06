package ru.geekbrains.april.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
