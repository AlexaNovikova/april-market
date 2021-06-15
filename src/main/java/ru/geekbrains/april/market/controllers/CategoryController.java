package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.services.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("/{id}")
    private Category getOneCategoryById(@PathVariable Long id){
     return categoryService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category doesn't exist. "+ id));
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.findAllCategories();
    }
}
