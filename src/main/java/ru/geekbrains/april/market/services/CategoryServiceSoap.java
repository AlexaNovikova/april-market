package ru.geekbrains.april.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.repositories.CategoryRepository;
import ru.geekbrains.april.market.soap.categories.CategorySoap;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryServiceSoap {
    private final CategoryRepository categoryRepository;

    public static final Function<Category, CategorySoap> functionEntityToSoap = ge -> {
        CategorySoap categorySoap = new CategorySoap();
        categorySoap.setId(ge.getId());
        categorySoap.setTitle(ge.getTitle());
        ge.getProducts().stream().map(ProductServiceSoap.functionEntityToSoap).forEach(p -> categorySoap.getProducts().add(p));
        return categorySoap;
    };

    public CategorySoap getById(Long id) {
        return categoryRepository.findById(id).map(functionEntityToSoap).get();
    }
}
