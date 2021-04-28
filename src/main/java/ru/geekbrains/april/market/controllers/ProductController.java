package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> showAllProducts()
    {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product showProductById(@PathVariable  Long id){
        return productService.findOneById(id).get();
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping
    public Product updateProductInfo(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
    }
}
