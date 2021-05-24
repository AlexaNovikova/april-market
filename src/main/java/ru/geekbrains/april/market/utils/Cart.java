package ru.geekbrains.april.market.utils;


import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Component;
import ru.geekbrains.april.market.dtos.ProductDto;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.OrderItem;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private BigDecimal totalPrice;

    @PostConstruct
    public void init(){
        items=new ArrayList<>();
        totalPrice=BigDecimal.ZERO;
    }

    private void recalculate(){
        totalPrice=BigDecimal.ZERO;
        for(OrderItem oi:items){
            totalPrice=totalPrice.add(oi.getPrice());
        }
    }

    public void addToCart(Long id) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }

        Product product = productService.findOneById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        items.add(new OrderItem(product));
        recalculate();
    }


    public void clear() {
        items.clear();
        recalculate();
    }

    public List<OrderItem> getItemsInCart(){
        return Collections.unmodifiableList(items);
    }

    public void deleteAllItems() {
        items.clear();
         recalculate();
    }

//    public void deleteOneProduct(Long id) {
//        Optional<Product> product = items.stream().filter((p) -> p.getId().equals(id)).findAny();
//        product.ifPresent(value -> items.remove(value));
//        totalPrice=totalPrice.subtract(product.get().getPrice());
//        itemsCount--;
//    }


    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
