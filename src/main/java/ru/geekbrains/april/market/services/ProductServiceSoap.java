package ru.geekbrains.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.repositories.ProductRepository;
import ru.geekbrains.april.market.soap.products.ProductSoap;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceSoap {
    private final ProductRepository productRepository;

    public static final Function<Product, ProductSoap> functionEntityToSoap = se -> {
        ProductSoap p = new ProductSoap();
        p.setId(se.getId());
        p.setTitle(se.getTitle());
        double price = se.getPrice().doubleValue();
        p.setPrice(price);
        p.setCategoryTitle(se.getCategory().getTitle());
        return p;
    };

    public List<ProductSoap> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductSoap getProductById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }
}
