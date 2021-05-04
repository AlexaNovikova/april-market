package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.error_handling.MarketError;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        return productService.findOneById(id).orElseThrow(()-> new ResourceNotFoundException("Product doesn't exist " +id));
    }

//    @ExceptionHandler
//    public ResponseEntity<MarketError> handleResourceNotFoundException(ResourceNotFoundException e){
//        MarketError error = new MarketError(HttpStatus.NOT_FOUND.value(), Collections.singleton(e.getMessage()));
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    @PostMapping
    public ResponseEntity<?> addNewProduct(@RequestBody Product product){
        List<String> errors = new ArrayList<>();
        if (product.getTitle().length()<3){
          errors.add("Too short title");
        }
        if (product.getPrice()<1){
            errors.add("Invalid product price");
        }
        if(errors.size()>1){
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }
        Product out = productService.save(product);
        return new ResponseEntity<>(out, HttpStatus.CREATED);
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
