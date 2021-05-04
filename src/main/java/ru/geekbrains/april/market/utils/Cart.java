package ru.geekbrains.april.market.utils;


import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Component;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Cart {

    private List<Product> items;

    @PostConstruct
    public void init(){
        items=new ArrayList<>();
    }

    public void add(Product product){
       items.add(product);
    }

    public List<Product> showItemsInCart(){
        return items;
    }

    public void deleteAllItems() {
        items.clear();
    }

    public void deleteOneProduct(Long id) {
        Optional<Product> product = items.stream().filter((p) -> p.getId().equals(id)).findAny();
        product.ifPresent(value -> items.remove(value));
    }
}
