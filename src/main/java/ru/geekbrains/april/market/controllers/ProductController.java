package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.dtos.OrderDto;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.dtos.ProductInfo;
import ru.geekbrains.april.market.error_handling.InvalidDataException;
import ru.geekbrains.april.market.error_handling.MarketError;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.models.Order;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.models.User;
import ru.geekbrains.april.market.repositories.specifications.ProductSpecifications;
import ru.geekbrains.april.market.services.CategoryService;
import ru.geekbrains.april.market.services.OrderService;
import ru.geekbrains.april.market.services.ProductService;
import ru.geekbrains.april.market.services.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam MultiValueMap<String, String> params,
                                           @RequestParam(name = "p", defaultValue = "1") int page) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(ProductSpecifications.build(params), page, 10);

    }

    @GetMapping("/{id}")
    public ProductInfo showProductInfoById(@PathVariable Long id) {
       Product product = productService.findOneById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exist " + id));
       return new ProductInfo(product);
    }

//    @ExceptionHandler
//    public ResponseEntity<MarketError> handleResourceNotFoundException(ResourceNotFoundException e){
//        MarketError error = new MarketError(HttpStatus.NOT_FOUND.value(), Collections.singleton(e.getMessage()));
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    //    @PostMapping
//    public ResponseEntity<?> addNewProduct(@RequestBody Product product){
//        List<String> errors = new ArrayList<>();
//        if (product.getTitle().length()<3){
//          errors.add("Too short title");
//        }
//        if (product.getPrice()<1){
//            errors.add("Invalid product price");
//        }
//        if(errors.size()>1){
//            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
//        }
//        Product out = productService.save(product);
//        return new ResponseEntity<>(out, HttpStatus.CREATED);
//    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return productService.createNewProduct(productDto);
    }

    @PostMapping("/addComment")
    public void addComment( @RequestParam String comment, @RequestParam Long id, @RequestParam String username) {
        productService.addComment(id, comment, username);
    }




    @PutMapping
    public ProductDto updateProductInfo(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
