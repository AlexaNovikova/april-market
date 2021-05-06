package ru.geekbrains.april.market.utils;


import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Component;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Cart {

    private List<Product> items;
    private int totalPrice;
    private int itemsCount;

    @PostConstruct
    public void init(){
        items=new ArrayList<>();
    }

    public void add(Product product){
       items.add(product);
       totalPrice+=product.getPrice();
       itemsCount++;
    }

    public List<Product> getItemsInCart(){
        return Collections.unmodifiableList(items);
    }

    public void deleteAllItems() {
        items.clear();
        totalPrice=0;
        itemsCount=0;
    }

    public void deleteOneProduct(Long id) {
        Optional<Product> product = items.stream().filter((p) -> p.getId().equals(id)).findAny();
        product.ifPresent(value -> items.remove(value));
        totalPrice-=product.get().getPrice();
        itemsCount--;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
