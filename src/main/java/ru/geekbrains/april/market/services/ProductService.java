package ru.geekbrains.april.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findOneById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Product save (Product product){
        return productRepository.save(product);
    }

}
