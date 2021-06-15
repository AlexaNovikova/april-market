package ru.geekbrains.april.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Category;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.repositories.ProductRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findPage(int page, int pageSize) {
        return productRepository.findAllBy(PageRequest.of(page, pageSize));
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }


    public Optional<Product> findOneById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }


    @Transactional
    public ProductDto createNewProduct (ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).
                orElseThrow(() -> new ResourceNotFoundException("Category doesn't exist " +
                        productDto.getCategoryTitle()));
        product.setCategory(category);
        product = productRepository.save(product);
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto){
        Product product = findOneById(productDto.getId()).orElseThrow(()->new ResourceNotFoundException("Product doesn't exist "+ productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).
                orElseThrow(() -> new ResourceNotFoundException("Category doesn't exist " +
                        productDto.getCategoryTitle()));
        product.setCategory(category);
        return new ProductDto(product);
    }

}
